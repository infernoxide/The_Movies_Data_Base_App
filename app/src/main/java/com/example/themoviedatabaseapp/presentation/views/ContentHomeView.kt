package com.example.themoviedatabaseapp.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.themoviedatabaseapp.R
import com.example.themoviedatabaseapp.presentation.viewmodel.MoviesViewModel
import com.example.themoviedatabaseapp.presentation.composables.MovieCard

@Composable
fun ContentHomeView(viewModel: MoviesViewModel, paddingValues: PaddingValues) {
    val moviesPage = viewModel.moviesPage.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .padding(horizontal = dimensionResource(R.dimen.size_10dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when (moviesPage.loadState.refresh) {
            is LoadState.Error -> {
                val error = (moviesPage.loadState.refresh as LoadState.Error).error
                error.message?.let {

                }
            }
            is LoadState.Loading -> {

            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                ) {
                    items(moviesPage.itemCount) { index ->
                        val item = moviesPage[index]
                        if (item != null) {
                            MovieCard (item) {

                            }
                            Box(
                                modifier = Modifier.fillMaxWidth().padding(bottom = dimensionResource(R.dimen.size_5dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = if (item.title!!.isNotEmpty()) item.title else stringResource(R.string.empty_name),
                                    fontSize = dimensionResource(R.dimen.size_20dp).value.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}