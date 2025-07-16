package com.example.themoviedatabaseapp.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.themoviedatabaseapp.domain.model.MovieModel
import com.example.themoviedatabaseapp.domain.repository.MoviesRepository

class MoviesDataSource (private val repository: MoviesRepository) : PagingSource<Int, MovieModel> () {

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = repository.getMoviesByPaging(nextPageNumber)
            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = if (response.results.isNotEmpty()) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}