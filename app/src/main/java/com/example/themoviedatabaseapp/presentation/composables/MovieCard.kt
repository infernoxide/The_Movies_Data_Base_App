package com.example.themoviedatabaseapp.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.themoviedatabaseapp.BuildConfig
import com.example.themoviedatabaseapp.R
import com.example.themoviedatabaseapp.domain.model.MovieModel

@Composable
fun MovieCard(movie: MovieModel, onclick: () -> Unit){
    Card(
        shape = RoundedCornerShape(dimensionResource(R.dimen.size_5dp)),
        modifier = Modifier
            .padding(vertical = dimensionResource(R.dimen.size_10dp))
            .shadow(dimensionResource(R.dimen.size_40dp))
            .clickable { onclick() }
    ) {
        Column {
            ShowImage(BuildConfig.IMAGES_BASE_URL+movie.poster_path)
        }
    }
}