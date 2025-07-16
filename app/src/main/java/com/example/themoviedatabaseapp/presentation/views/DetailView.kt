package com.example.themoviedatabaseapp.presentation.views

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.themoviedatabaseapp.R
import com.example.themoviedatabaseapp.presentation.composables.MainTopBar
import com.example.themoviedatabaseapp.presentation.uistate.UiState
import com.example.themoviedatabaseapp.presentation.viewmodel.MoviesViewModel

@Composable
fun DetailView(viewModel: MoviesViewModel, navController: NavController, id:Int, name:String?){
    LaunchedEffect(Unit) {
        if (id == 0){
            name?.let { value ->
                //todo Get Game by name
            }
        }else{
            viewModel.getMovieByID(id)
        }
    }
    Scaffold(
        topBar = {
            MainTopBar(title = setTitle(viewModel), showBackButton = true) {
                navController.popBackStack()
            }
        }
    ) { paddingValues ->
        ContentDetailView(viewModel, paddingValues)
    }
}

@Composable
fun setTitle(viewModel: MoviesViewModel):String{
    return when (val state = viewModel.movieByIdState){
        is UiState.Loading -> stringResource(R.string.loading)
        is UiState.Success -> state.data.title
        is UiState.Error -> stringResource(R.string.error)
    }
}