package com.example.themoviedatabaseapp.presentation.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.themoviedatabaseapp.R
import com.example.themoviedatabaseapp.data.local.room.entities.MovieTable
import com.example.themoviedatabaseapp.di.NetworkMonitor
import com.example.themoviedatabaseapp.domain.usescase.GetMovieByIDUseCase
import com.example.themoviedatabaseapp.domain.usescase.GetMoviesByPagingUseCase
import com.example.themoviedatabaseapp.presentation.uistate.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMovieByIdUseCase: GetMovieByIDUseCase,
    private val getMoviesByPagingUseCase: GetMoviesByPagingUseCase,
    private val networkMonitor: NetworkMonitor,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private var pendingAction: (() -> Unit) ?= null
    val moviesPage = getMoviesByPagingUseCase().cachedIn(viewModelScope)
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
            val result = getMovieByIdUseCase(id)

            movieByIdState = if (result != null) {
                UiState.Success(result)
            } else {
                if (networkMonitor.isConnected.value) {
                    UiState.Error(context.getString(R.string.id_not_found, id.toString()))
                } else {
                    pendingAction = { getMovieByID(id) }
                    UiState.Error(context.getString(R.string.no_internet_connection))
                }
            }
        }
    }

}