package com.example.themoviedatabaseapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.themoviedatabaseapp.core.Constants.Companion.NAV_DETAIL_VIEW
import com.example.themoviedatabaseapp.core.Constants.Companion.NAV_HOME
import com.example.themoviedatabaseapp.core.Constants.Companion.NAV_SEARCH_VIEW
import com.example.themoviedatabaseapp.presentation.viewmodel.MoviesViewModel
import com.example.themoviedatabaseapp.presentation.views.DetailView
import com.example.themoviedatabaseapp.presentation.views.HomeView
import com.example.themoviedatabaseapp.presentation.views.SearchView

@Composable
fun NavManager(viewModel: MoviesViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NAV_HOME) {
        composable(route = NAV_HOME) {
            HomeView(viewModel = viewModel, navController)
        }
        composable(
            route = "$NAV_DETAIL_VIEW/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )) { navStackEntry ->
            val id = navStackEntry.arguments?.getInt("id") ?: 0
            DetailView(viewModel = viewModel, navController, id)
        }
        composable(
            route = "$NAV_SEARCH_VIEW/{name}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType }
            )) { navBackStackEntry ->
            val name = navBackStackEntry.arguments?.getString("name") ?: ""
            SearchView(viewModel, navController, name)
        }
    }
}