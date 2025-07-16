package com.example.themoviedatabaseapp.presentation.views

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.themoviedatabaseapp.R
import com.example.themoviedatabaseapp.presentation.viewmodel.MoviesViewModel
import com.example.themoviedatabaseapp.presentation.composables.MainTopBar

@Composable
fun HomeView(viewModel: MoviesViewModel) {

    Scaffold(
        topBar = {
            MainTopBar(title = stringResource(R.string.title_home_view)) { }
        }

    ) { paddingValues ->
        ContentHomeView(viewModel, paddingValues)
    }

}