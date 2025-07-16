package com.example.themoviedatabaseapp.di

import android.content.Context
import androidx.room.Room
import com.example.themoviedatabaseapp.BuildConfig
import com.example.themoviedatabaseapp.data.local.room.MoviesDatabase
import com.example.themoviedatabaseapp.data.local.room.dao.MoviesDAO
import com.example.themoviedatabaseapp.data.remote.AuthInterceptor
import com.example.themoviedatabaseapp.data.remote.MoviesAPI
import com.example.themoviedatabaseapp.data.repository.MoviesRepositoryImpl
import com.example.themoviedatabaseapp.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAuthInterceptor(): Interceptor = AuthInterceptor()

    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient) // <<--- Aquí se usa el OkHttpClient con el interceptor
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesMoviesAPI(retrofit: Retrofit): MoviesAPI {
        return retrofit.create(MoviesAPI::class.java)
    }

    @Singleton
    @Provides
    fun providesMoviesDAO(moviesDatabase: MoviesDatabase) : MoviesDAO {
        return moviesDatabase.moviesDAO()
    }

    @Singleton
    @Provides
    fun providesMoviesDataBase(@ApplicationContext context: Context) : MoviesDatabase {
        return Room.databaseBuilder(
            context,
            MoviesDatabase::class.java,
            "movies_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(
        moviesAPI: MoviesAPI,
        moviesDAO: MoviesDAO,
        networkMonitor: NetworkMonitor
    ): MoviesRepository {
        return MoviesRepositoryImpl(moviesAPI, moviesDAO, networkMonitor)
    }

}