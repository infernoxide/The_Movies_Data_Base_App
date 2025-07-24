package com.example.themoviedatabaseapp.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.themoviedatabaseapp.R
import com.example.themoviedatabaseapp.presentation.uistate.UiState
import com.example.themoviedatabaseapp.presentation.viewmodel.MoviesViewModel

@Composable
fun setTitle(viewModel: MoviesViewModel):String{
    return when (val state = viewModel.movieByIdState){
        is UiState.Loading -> stringResource(R.string.loading)
        is UiState.Success -> state.data.title
        is UiState.Error -> stringResource(R.string.error)
    }
}