package com.example.themoviedatabaseapp.data.datasource

sealed class PagingParams {
    data class ByPage(val page: Int) : PagingParams()
    data class ByName(val query: String, val page: Int) : PagingParams()
}