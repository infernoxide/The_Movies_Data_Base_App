package com.example.themoviedatabaseapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.themoviedatabaseapp.data.datasource.MoviesDataSource
import com.example.themoviedatabaseapp.domain.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    val moviesPage = Pager(PagingConfig(pageSize = 20)){
        MoviesDataSource(repository)
    }.flow.cachedIn(viewModelScope)

}