package com.example.composemoviesapp.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composemoviesapp.data.MovieRepository
import com.example.composemoviesapp.data.model.Movie
import com.example.composemoviesapp.ui.widgets.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(movieRepository: MovieRepository, onNavigateToDetailsScreen: (String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Movies App",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            MainContent(movieRepository.getMovies(), onNavigateToDetailsScreen)
        }
    }
}

@Composable
private fun MainContent(movies: List<Movie>, onMovieItemClick: (String) -> Unit) {
    MoviesList(movies = movies, onMovieItemClick)
}

@Composable
private fun MoviesList(movies: List<Movie>, onMovieItemClick: (String) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(12.dp)
    ) {
        items(movies) {
            MovieRow(movie = it, onItemClick = onMovieItemClick)
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    HomeScreen(MovieRepository()) {

    }
}