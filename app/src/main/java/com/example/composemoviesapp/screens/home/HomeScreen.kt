package com.example.composemoviesapp.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onNavigateToDetailsScreen: (String) -> Unit) {
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
            MainContent(onNavigateToDetailsScreen)
        }
    }
}

@Composable
private fun MainContent(onMovieItemClick: (String) -> Unit) {
    val movies = listOf(
        "Harry Potter",
        "Justice League",
        "300",
        "Avengers: Endgame",
        "Spiderman noway home",
        "Avengers: Infinity War",
        "The Batman",
        "Accountant",
        "Army of the Dead",
        "Extraction",
        "Inception",
        "Lords of the Rings: The Two Towers",
        "Lords of the Rings: The Return of the King"
    )
    MoviesList(movies = movies, onMovieItemClick)
}

@Composable
private fun MoviesList(movies: List<String>, onMovieItemClick: (String) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(12.dp)
    ) {
        items(movies) {
            MovieRow(movie = it, onMovieItemClick)
        }
    }
}

@Composable
private fun MovieRow(movie: String, onItemClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(120.dp)
            .clickable { onItemClick(movie) },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RectangleShape),
                imageVector = Icons.Default.AccountBox,
                contentDescription = "Movie image"
            )
            Text(text = movie)
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    HomeScreen {

    }
}