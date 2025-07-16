package com.example.themoviedatabaseapp.domain.model

data class MovieDetails(
    val id : Int ?= 0,
    val backdrop_path : String ?= "",
    val home_page : String ?= "",
    val overview : String ?= "",
    val release_date : String ?= "",
    val title : String ?= ""
)
