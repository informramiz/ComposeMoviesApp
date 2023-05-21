package com.example.composemoviesapp.navigation

import androidx.compose.runtime.Composable
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
    NavHost(navController = navController, startDestination = AppScreen.Home.name) {
        composable(AppScreen.Home.name) {
            HomeScreen(
                MovieRepository(),
                onNavigateToDetailsScreen = { movie ->
                    navController.navigate(route = AppScreen.MovieDetails.name + "/$movie")
                }
            )
        }

        composable(
            AppScreen.MovieDetails.name + "/{movie}",
            arguments = listOf(
                navArgument("movie") { NavType.StringType }
            )
        ) { backStackEntry ->
            MovieDetailsScreen(
                movie = backStackEntry.arguments!!.getString("movie")!!,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}