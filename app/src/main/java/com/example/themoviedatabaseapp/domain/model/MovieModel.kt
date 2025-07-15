package com.example.themoviedatabaseapp.domain.model

data class MovieModel(
    val id: Int ?= 0,
    val poster_path : String? = "",
    val title : String ?= ""
)
