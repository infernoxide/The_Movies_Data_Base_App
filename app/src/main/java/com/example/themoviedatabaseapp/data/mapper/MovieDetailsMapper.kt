package com.example.themoviedatabaseapp.data.mapper

import com.example.themoviedatabaseapp.data.local.room.entities.MovieTable
import com.example.themoviedatabaseapp.domain.model.MovieDetails

fun MovieDetails.toEntity(): MovieTable{
    return MovieTable(
        id = this.id?.toLong() ?: 0L,
        backdrop_path = this.backdrop_path ?: "",
        homepage = this.homepage ?: "",
        overview = this.overview ?: "",
        release_date = this.release_date ?: "",
        title = this.title ?: ""
    )
}