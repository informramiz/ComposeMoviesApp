package com.example.composemoviesapp.screens.moviedetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composemoviesapp.data.MovieRepository
import com.example.composemoviesapp.ui.widgets.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(movieId: String, movieRepository: MovieRepository, onNavigateBack: () -> Unit) {
    val movie = remember {
        movieRepository.getMovieById(movieId)!!
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Movie Details",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Up", tint = MaterialTheme.colorScheme.onPrimary)
                    }
                }
            )
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier.padding(contentPadding),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MovieRow(modifier = Modifier.padding(12.dp), movie = movie, onItemClick = {})
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Movie Posters", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(20.dp))
                HorizontalScrollableImagesView(images = movie.images)
            }
        }
    }
}

@Composable
fun HorizontalScrollableImagesView(images: List<String>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        items(images) {image ->
            Card(
                modifier = Modifier
                    .size(240.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp)),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Preview
@Composable
private fun MovieDetailDefaultPreview() {
    MovieDetailsScreen(MovieRepository().getMovies().first().id, MovieRepository()) {

    }
}