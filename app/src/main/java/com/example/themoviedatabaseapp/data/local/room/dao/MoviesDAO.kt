package com.example.themoviedatabaseapp.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themoviedatabaseapp.data.local.room.entities.MovieTable
import com.example.themoviedatabaseapp.data.local.room.entities.MoviesHomeTable
import com.example.themoviedatabaseapp.domain.model.MovieDetails

@Dao
interface MoviesDAO {

    @Query("SELECT * FROM MoviesHomeTable WHERE page = :page ORDER BY id ASC")
    suspend fun getMoviesHomeByPage(page: Int): List<MoviesHomeTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoviesHomeList(movies: List<MoviesHomeTable>)

    @Query("DELETE FROM MoviesHomeTable")
    suspend fun clearMoviesHome()

    @Query("SELECT * FROM MovieTable WHERE id = :id")
    suspend fun getMovieDetails(id: Long): MovieTable?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetails(movieDetails: MovieTable)

    @Query("DELETE FROM MovieTable")
    suspend fun clearMovieDetails()
}