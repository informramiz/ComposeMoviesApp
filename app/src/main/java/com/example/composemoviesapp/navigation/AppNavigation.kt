package com.example.composemoviesapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composemoviesapp.data.MovieRepository
import com.example.composemoviesapp.screens.AppScreen
import com.example.composemoviesapp.screens.home.HomeScreen
import com.example.composemoviesapp.screens.moviedetails.MovieDetailsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val movieRepository = remember {
        MovieRepository()
    }
    NavHost(navController = navController, startDestination = AppScreen.Home.name) {
        composable(AppScreen.Home.name) {
            HomeScreen(
                movieRepository,
                onNavigateToDetailsScreen = { movie ->
                    navController.navigate(route = AppScreen.MovieDetails.name + "/$movie")
                }
            )
        }

        composable(
            AppScreen.MovieDetails.name + "/{movieId}",
            arguments = listOf(
                navArgument("movieId") { NavType.StringType }
            )
        ) { backStackEntry ->
            MovieDetailsScreen(
                movieId = backStackEntry.arguments!!.getString("movieId")!!,
                movieRepository = movieRepository,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}