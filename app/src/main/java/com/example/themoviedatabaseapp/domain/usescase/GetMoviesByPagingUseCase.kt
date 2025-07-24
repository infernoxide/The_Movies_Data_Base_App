package com.example.themoviedatabaseapp.domain.usescase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.themoviedatabaseapp.data.datasource.MoviesDataSource
import com.example.themoviedatabaseapp.data.datasource.PagingParams
import com.example.themoviedatabaseapp.domain.model.MovieModel
import com.example.themoviedatabaseapp.domain.repository.MoviesRepository
import javax.inject.Inject

class GetMoviesByPagingUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    fun getNowPlayingPager(): Pager<Int, MovieModel> {
        return Pager(PagingConfig(pageSize = 20)) {
            MoviesDataSource(
                loadFunction = { param ->
                    when (param) {
                        is PagingParams.ByPage -> repository.getMoviesByPaging(param.page)
                        else -> throw IllegalArgumentException("Unsupported param")
                    }
                },
                createParams = { page -> PagingParams.ByPage(page) }
            )
        }
    }

    fun getMoviesByNamePager(query: String): Pager<Int, MovieModel> {
        return Pager(PagingConfig(pageSize = 20)) {
            MoviesDataSource(
                loadFunction = { param ->
                    when (param) {
                        is PagingParams.ByName -> repository.getMovieByName(param.query, param.page)
                        else -> throw IllegalArgumentException("Unsupported param")
                    }
                },
                createParams = { page -> PagingParams.ByName(query, page) }
            )
        }
    }
}