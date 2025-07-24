package com.example.themoviedatabaseapp.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.rememberImagePainter
import com.example.themoviedatabaseapp.BuildConfig
import com.example.themoviedatabaseapp.R

@Composable
fun ShowImage(image: String){

    if (image.length > 32){
        val movieCover = rememberImagePainter(
            data = image,
            builder = {
                placeholder(R.drawable.tmbd_place_holder)
                error(R.drawable.tmbd_place_holder)
                fallback(R.drawable.tmbd_place_holder)
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
    }else{
        Image(
            painter = painterResource(R.drawable.tmbd_place_holder),
            contentDescription = stringResource(R.string.movie_cover),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.size_500dp))
        )
    }

}