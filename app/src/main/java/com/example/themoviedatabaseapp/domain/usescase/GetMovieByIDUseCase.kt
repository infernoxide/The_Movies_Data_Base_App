package com.example.themoviedatabaseapp.domain.usescase

import com.example.themoviedatabaseapp.data.local.room.entities.MovieTable
import com.example.themoviedatabaseapp.domain.repository.MoviesRepository
import javax.inject.Inject

class GetMovieByIDUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    suspend operator fun invoke(id: Int): MovieTable? {
        return repository.getMovieByID(id)
    }
}