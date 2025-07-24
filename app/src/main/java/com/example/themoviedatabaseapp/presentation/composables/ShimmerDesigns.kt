package com.example.themoviedatabaseapp.presentation.composables

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.themoviedatabaseapp.R

@Composable
fun SetSpace(value: Int, alignment : Int) {
    if (alignment == 1)
        Spacer(modifier = Modifier.height(value.dp))
    else
        Spacer(modifier = Modifier.width(value.dp))
}

@Composable
fun SetBox(width: Float, height: Int, applyCorners: Boolean = false) {
    val shimmerAlpha = rememberInfiniteTransition(
        label = stringResource(R.string.shimmer_home)
    ).animateFloat(
        initialValue = 0f, targetValue = 1f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 600), repeatMode = RepeatMode.Reverse
        ), label = stringResource(R.string.shimmer_home)
    ).value
    Box(
        modifier = Modifier
            .height(height.dp)
            .fillMaxWidth(width)
            .background(
                Color.LightGray.copy(alpha = shimmerAlpha),
                shape = if (applyCorners) RoundedCornerShape(dimensionResource(R.dimen.size_10dp)) else RoundedCornerShape(
                    0.dp
                )
            )
    )
}

@Composable
fun ShimmerHomeResults() {
    SetSpace(16, 1)
    SetBox(1f, 35)
    SetSpace(16, 1)
    repeat(3) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.size_10dp))
        ) {
            Column {
                SetBox(1f, 500, true)
                SetSpace(16, 1)
                SetBox(0.7f, 25)
                SetSpace(8, 1)
            }
        }
    }
}

@Composable
fun ShimmerDetailView() {
    SetBox(1f, 500)
    SetSpace(10, 1)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(R.dimen.size_10dp))
    ) {
        Row(modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.size_10dp))) {
                SetBox(0.5f, 30)
                SetSpace(15,2)
                SetBox(0.5f, 30, true)
        }
        Column(modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.size_10dp))) {
            SetSpace(20,1)
            SetBox(1f, 15)
            SetSpace(10,1)
            SetBox(1f, 15)
            SetSpace(10,1)
            SetBox(0.5f, 15)
        }
    }
}

@Composable
fun ShimmerResults(){
    repeat(3) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.size_10dp))
        ) {
            Column {
                SetBox(1f, 500, true)
                SetSpace(16, 1)
                SetBox(0.7f, 25)
                SetSpace(8, 1)
            }
        }
    }
}