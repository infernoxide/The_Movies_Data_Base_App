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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.themoviedatabaseapp.R
import com.example.themoviedatabaseapp.core.Constants.Companion.NAV_DETAIL_VIEW
import com.example.themoviedatabaseapp.presentation.composables.MovieCard
import com.example.themoviedatabaseapp.presentation.composables.ShimmerHomeResults
import com.example.themoviedatabaseapp.presentation.viewmodel.MoviesViewModel

@Composable
fun SearchDetailView(
    viewModel: MoviesViewModel,
    paddingValues: PaddingValues,
    navController: NavController,
    name: String
) {
    val searchResults = remember(name) {
        viewModel.getMoviesByName(name)
    }.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .padding(horizontal = dimensionResource(R.dimen.size_10dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when (searchResults.loadState.refresh) {
            is LoadState.Error -> {
                val error = (searchResults.loadState.refresh as LoadState.Error).error
                error.message?.let {
                    ErrorView(
                        buttonText = stringResource(R.string.retry),
                        error = it,
                        onRetry = { searchResults.retry() }
                    )
                }
            }
            is LoadState.Loading -> {
                ShimmerHomeResults()
            }
            else -> {
                if (searchResults.itemCount == 0){
                    ErrorView(
                        error = stringResource(R.string.movie_not_found),
                        onRetry = { }
                    )
                }else{
                    LazyColumn {
                        items(searchResults.itemCount) { index ->
                            val item = searchResults[index]
                            if (item != null) {
                                MovieCard (item) {
                                    navController.navigate("$NAV_DETAIL_VIEW/${item.id}")
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = dimensionResource(R.dimen.size_5dp)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = if (item.name!!.isNotEmpty()) item.name else stringResource(R.string.empty_name),
                                        fontSize = dimensionResource(R.dimen.size_20dp).value.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color.Black,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}