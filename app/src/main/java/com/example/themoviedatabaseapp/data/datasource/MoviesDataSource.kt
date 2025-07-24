package com.example.themoviedatabaseapp.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.themoviedatabaseapp.domain.model.MovieModel
import com.example.themoviedatabaseapp.domain.model.MoviesList

class MoviesDataSource(
    private val loadFunction: suspend (PagingParams) -> MoviesList,
    private val createParams: (Int) -> PagingParams
) : PagingSource<Int, MovieModel>() {

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        return try {
            val nextPage = params.key ?: 1
            val pagingParams = createParams(nextPage)
            val response = loadFunction(pagingParams)

            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = if (response.results.isNotEmpty()) nextPage + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}