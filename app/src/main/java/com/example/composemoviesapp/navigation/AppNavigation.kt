package com.example.composemoviesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composemoviesapp.screens.AppScreen
import com.example.composemoviesapp.screens.home.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreen.HomeScreen.name) {
        composable(AppScreen.HomeScreen.name) {
            HomeScreen {
                //TODO: Go to details screen
            }
        }
    }
}