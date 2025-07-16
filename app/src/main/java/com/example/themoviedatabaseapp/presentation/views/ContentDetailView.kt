package com.example.themoviedatabaseapp.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import com.example.themoviedatabaseapp.BuildConfig
import com.example.themoviedatabaseapp.R
import com.example.themoviedatabaseapp.presentation.composables.CardMovieInfo
import com.example.themoviedatabaseapp.presentation.composables.ShimmerDetailView
import com.example.themoviedatabaseapp.presentation.composables.ShowImage
import com.example.themoviedatabaseapp.presentation.uistate.UiState
import com.example.themoviedatabaseapp.presentation.viewmodel.MoviesViewModel

@Composable
fun ContentDetailView(viewModel: MoviesViewModel, paddingValues: PaddingValues) {
    val scroll = rememberScrollState(0)
    val stateLoading = viewModel.movieByIdState

    Column(
        Modifier
            .padding(paddingValues)
            .background(Color.White)
            .verticalScroll(scroll)
    ) {

        when (stateLoading) {
            is UiState.Loading -> ShimmerDetailView()
            is UiState.Success -> {
                ShowImage(image = BuildConfig.IMAGES_BASE_URL+stateLoading.data.backdrop_path)
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.size_10dp)))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = dimensionResource(R.dimen.size_20dp),
                            end = dimensionResource(R.dimen.size_5dp)
                        )
                ) {
                    CardMovieInfo(stateLoading.data.homepage, stateLoading.data.release_date)
                }
                Text(
                    text = stateLoading.data.overview,
                    color = Color.Black,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(
                            horizontal = dimensionResource(R.dimen.size_15dp),
                            vertical = dimensionResource(R.dimen.size_10dp)
                        )
                )
            }
            is UiState.Error -> {
                ErrorView(
                    error = stateLoading.message,
                    onRetry = {}
                )
            }
        }
    }
}