package com.example.themoviedatabaseapp.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import coil.compose.rememberImagePainter
import com.example.themoviedatabaseapp.R

@Composable
fun ShowImage(image: String){
    val movieCover = rememberImagePainter(
        data = image,
        builder = {
            placeholder(R.drawable.icon_image_placeholder)
            error(R.drawable.icon_image_not_found)
            fallback(R.drawable.icon_image_not_found)
        }
    )

    Image(
        painter = movieCover,
        contentDescription = stringResource(R.string.movie_cover),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.size_500dp))
    )
}