package com.example.themoviedatabaseapp.data.repository

import com.example.themoviedatabaseapp.core.Constants
import com.example.themoviedatabaseapp.data.local.room.dao.MoviesDAO
import com.example.themoviedatabaseapp.data.local.room.entities.MovieTable
import com.example.themoviedatabaseapp.data.mapper.toEntity
import com.example.themoviedatabaseapp.data.remote.MoviesAPI
import com.example.themoviedatabaseapp.di.NetworkMonitor
import com.example.themoviedatabaseapp.domain.model.MovieModel
import com.example.themoviedatabaseapp.domain.model.MoviesList
import com.example.themoviedatabaseapp.domain.repository.MoviesRepository
import okio.IOException
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesAPI: MoviesAPI,
    private val moviesDAO: MoviesDAO,
    private val networkMonitor: NetworkMonitor
) : MoviesRepository {

    override suspend fun getMoviesByPaging(page: Int): MoviesList {
        val localMovies = moviesDAO.getMoviesHomeByPage(page)

        if (localMovies.isNotEmpty()) {
            return MoviesList(
                page = localMovies.size,
                results = localMovies.map {
                    MovieModel(
                        id = it.id.toInt(),
                        poster_path = it.poster_path,
                        title = it.title
                    )
                }
            )
        }

        if (!networkMonitor.isConnected.value) {
            throw IOException("Sin conexión a internet")
        }

        val response = moviesAPI.getMoviesByPaging(Constants.LANGUAGE_PARAM, page)
        val moviesToCache = response.results.map { it.toEntity(page) }
        moviesDAO.insertMoviesHomeList(moviesToCache)

        return response
    }

    override suspend fun getMovieByID(id: Int): MovieTable? {
        val localMovies = moviesDAO.getMovieDetails(id.toLong())

        if (localMovies != null) return localMovies
        if (!networkMonitor.isConnected.value) return null

        val response = moviesAPI.getMovieByID(id, Constants.LANGUAGE_PARAM)
        if (response.isSuccessful) {
            val remote = response.body()
            if (remote != null) {
                val entity = remote.toEntity()
                moviesDAO.insertMovieDetails(entity)
                return entity
            }
        }

        return null
    }
}