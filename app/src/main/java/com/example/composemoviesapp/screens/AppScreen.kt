package com.example.composemoviesapp.screens

enum class AppScreen {
    HomeScreen,
    DetailsScreen;

    companion object {
        fun fromRoute(route: String?): AppScreen {
            return when (route?.substringBefore("/")) {
                HomeScreen.name, null -> HomeScreen
                DetailsScreen.name -> DetailsScreen
                else -> throw IllegalArgumentException("Route $route is not recognized")
            }
        }
    }
}