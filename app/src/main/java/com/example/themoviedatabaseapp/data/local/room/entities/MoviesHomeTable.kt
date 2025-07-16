package com.example.themoviedatabaseapp.data.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MoviesHomeTable")
data class MoviesHomeTable(
    @PrimaryKey(autoGenerate = false)
    val id:Long,
    @ColumnInfo(name = "poster_path")
    val poster_path: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "page")
    val page: Int = 0
)
