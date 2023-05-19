package com.example.composemoviesapp.screens.moviedetails

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MovieDetails(movie: String) {
    Text(text = "Movie argument: $movie")
}