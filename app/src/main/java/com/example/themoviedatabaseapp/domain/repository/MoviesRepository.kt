package com.example.themoviedatabaseapp.domain.repository

import com.example.themoviedatabaseapp.data.remote.MoviesAPI
import com.example.themoviedatabaseapp.domain.model.MoviesList
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesAPI: MoviesAPI
) {

    suspend fun getMoviesByPaging(language: String, page: Int): MoviesList {
        return moviesAPI.getMoviesByPaging(language, page)
    }

}