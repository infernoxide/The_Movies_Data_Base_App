package com.example.themoviedatabaseapp.presentation.composables

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.themoviedatabaseapp.R

@Composable
fun CardMovieInfo(url: String, releaseDate: String){
    val context = LocalContext.current
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

    Row (verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = stringResource(R.string.release_date)+releaseDate,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = dimensionResource(R.dimen.size_15dp).value.sp,
            modifier = Modifier.padding(vertical = dimensionResource(R.dimen.size_10dp))
        )
        if (url.isNotEmpty()) {
            Button(
                modifier = Modifier.padding(start = dimensionResource(R.dimen.size_30dp)),
                onClick = {
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color.DarkGray
                )
            ) {
                Text(text = stringResource(R.string.website))
            }
        }
    }
}