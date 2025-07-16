package com.example.themoviedatabaseapp.domain.repository

import com.example.themoviedatabaseapp.data.local.room.entities.MovieTable
import com.example.themoviedatabaseapp.domain.model.MoviesList

interface MoviesRepository {
    suspend fun getMoviesByPaging(page: Int): MoviesList
    suspend fun getMovieByID(id: Int): MovieTable?
}