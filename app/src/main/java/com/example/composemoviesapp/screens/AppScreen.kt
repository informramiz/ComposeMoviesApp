package com.example.composemoviesapp.screens

enum class AppScreen {
    Home,
    MovieDetails;

    companion object {
        fun fromRoute(route: String?): AppScreen {
            return when (route?.substringBefore("/")) {
                Home.name, null -> Home
                MovieDetails.name -> MovieDetails
                else -> throw IllegalArgumentException("Route $route is not recognized")
            }
        }
    }
}