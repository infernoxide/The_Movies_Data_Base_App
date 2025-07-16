package com.example.themoviedatabaseapp.data.mapper

import com.example.themoviedatabaseapp.data.local.room.entities.MoviesHomeTable
import com.example.themoviedatabaseapp.domain.model.MovieModel

fun MovieModel.toEntity(page:Int): MoviesHomeTable {
    return MoviesHomeTable(
        id = this.id?.toLong() ?: 0L,
        poster_path = this.poster_path ?: "",
        title = this.title ?: "",
        page = page
    )
}