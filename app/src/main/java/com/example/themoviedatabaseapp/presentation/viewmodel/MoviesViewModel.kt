package com.example.themoviedatabaseapp.presentation.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.themoviedatabaseapp.R
import com.example.themoviedatabaseapp.data.datasource.MoviesDataSource
import com.example.themoviedatabaseapp.data.local.room.entities.MovieTable
import com.example.themoviedatabaseapp.di.NetworkMonitor
import com.example.themoviedatabaseapp.domain.repository.MoviesRepository
import com.example.themoviedatabaseapp.presentation.uistate.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepository,
    private val networkMonitor: NetworkMonitor,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private var pendingAction: (() -> Unit) ?= null
    val moviesPage = Pager(PagingConfig(pageSize = 20)){
        MoviesDataSource(repository)
    }.flow.cachedIn(viewModelScope)
    var movieByIdState: UiState<MovieTable> by mutableStateOf(UiState.Loading)
        private set

    init {
        networkMonitor.start()

        viewModelScope.launch {
            networkMonitor.isConnected.collect { isConnected ->
                if (isConnected) {
                    pendingAction?.invoke()
                    pendingAction = null
                }
            }
        }
    }

    fun getMovieByID(id: Int) {
        viewModelScope.launch {
            movieByIdState = UiState.Loading
            val result = repository.getMovieByID(id)
            if (networkMonitor.isConnected.value) {
                movieByIdState = if (result != null) {
                    UiState.Success(result)
                } else {
                    UiState.Error(context.getString(R.string.id_not_found, id.toString()))
                }
            } else {
                movieByIdState = if (result != null) {
                    UiState.Success(result)
                } else {
                    pendingAction = { getMovieByID(id) }
                    UiState.Error(context.getString(R.string.no_internet_connection))
                }
            }
        }
    }

}