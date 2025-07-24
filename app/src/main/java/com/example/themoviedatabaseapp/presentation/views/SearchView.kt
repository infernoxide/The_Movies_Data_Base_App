package com.example.themoviedatabaseapp.presentation.views

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.themoviedatabaseapp.R
import com.example.themoviedatabaseapp.presentation.composables.MainTopBar
import com.example.themoviedatabaseapp.presentation.viewmodel.MoviesViewModel

@Composable
fun SearchView(viewModel: MoviesViewModel, navController: NavController, name:String?){
    Scaffold(
        topBar = {
            MainTopBar(title = stringResource(R.string.search), showBackButton = true) {
                navController.popBackStack()
            }
        }
    ) { paddingValues ->
        if (name != null) {
            SearchDetailView(viewModel, paddingValues, navController, name)
        }
    }
}