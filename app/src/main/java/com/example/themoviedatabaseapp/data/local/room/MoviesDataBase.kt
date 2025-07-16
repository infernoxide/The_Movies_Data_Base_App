package com.example.themoviedatabaseapp.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.themoviedatabaseapp.data.local.room.dao.MoviesDAO
import com.example.themoviedatabaseapp.data.local.room.entities.MovieTable
import com.example.themoviedatabaseapp.data.local.room.entities.MoviesHomeTable

@Database(entities = [MoviesHomeTable::class, MovieTable::class], version = 1, exportSchema = false)
abstract class MoviesDatabase: RoomDatabase() {
    abstract fun moviesDAO(): MoviesDAO
}