package com.example.themoviedatabaseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.themoviedatabaseapp.presentation.viewmodel.MoviesViewModel
import com.example.themoviedatabaseapp.presentation.views.HomeView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel: MoviesViewModel by viewModels()
        setContent {
            HomeView(viewModel)
        }
    }
}

@Composable
fun TestPaging(viewModel: MoviesViewModel, innerPadding: PaddingValues) {
    val moviesPage = viewModel.moviesPage.collectAsLazyPagingItems()

    LazyColumn {
        items(moviesPage.itemCount) { index ->
            val item = moviesPage[index]
            if (item != null) {
                Text(
                    text = item.title ?: "sin nombre",
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )
            }
        }
    }

}