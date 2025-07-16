package com.example.themoviedatabaseapp.data.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieTable")
data class MovieTable(
    @PrimaryKey(autoGenerate = false)
    val id:Long,
    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String,
    @ColumnInfo(name = "home_page")
    val home_page: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "release_date")
    val release_date: String,
    @ColumnInfo(name = "title")
    val title: String
)
