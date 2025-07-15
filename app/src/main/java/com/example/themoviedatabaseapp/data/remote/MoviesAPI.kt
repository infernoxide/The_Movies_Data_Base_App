package com.example.themoviedatabaseapp.data.remote

import com.example.themoviedatabaseapp.BuildConfig
import com.example.themoviedatabaseapp.domain.model.MoviesList
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {

    @GET("movie/now_playing")
    suspend fun getMoviesByPaging(
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): MoviesList

}