package com.example.themoviedatabaseapp.domain.usescase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.themoviedatabaseapp.data.datasource.MoviesDataSource
import com.example.themoviedatabaseapp.domain.model.MovieModel
import com.example.themoviedatabaseapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesByPagingUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    operator fun invoke(): Flow<PagingData<MovieModel>> {
        return Pager(PagingConfig(pageSize = 20)) {
            MoviesDataSource(repository)
        }.flow
    }
}