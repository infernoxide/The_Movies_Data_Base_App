package com.example.themoviedatabaseapp.domain.model

data class MoviesList(
    val page:Int? = 0,
    val results:List<MovieModel>
)
