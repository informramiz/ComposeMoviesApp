package com.example.composemoviesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composemoviesapp.screens.AppScreen
import com.example.composemoviesapp.screens.home.HomeScreen
import com.example.composemoviesapp.screens.moviedetails.MovieDetails

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreen.Home.name) {
        composable(AppScreen.Home.name) {
            HomeScreen {
                navController.navigate(route = AppScreen.MovieDetails.name)
            }
        }

        composable(AppScreen.MovieDetails.name) {
            MovieDetails()
        }
    }
}