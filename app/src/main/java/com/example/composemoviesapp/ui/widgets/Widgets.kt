package com.example.composemoviesapp.ui.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composemoviesapp.R
import com.example.composemoviesapp.data.MovieRepository
import com.example.composemoviesapp.data.model.Movie

@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit) {
    val expandedState = remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onItemClick(movie.id) },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.images.first())
                    .crossfade(true)
                    .build(),
                contentDescription = "Movie Image",
                placeholder = rememberVectorPainter(image = Icons.Default.Image),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = annotatedString("Director: ", movie.director),
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    text = annotatedString("Released: ", movie.year),
                    style = MaterialTheme.typography.bodySmall
                )

                AnimatedVisibility(visible = expandedState.value) {
                    Column {
                        Divider()
                        Text(
                            modifier = Modifier.padding(top = 4.dp),
                            text = annotatedString("Plot: ", movie.plot),
                            style = MaterialTheme.typography.bodySmall
                        )

                        Text(
                            modifier = Modifier.padding(top = 4.dp),
                            text = annotatedString("Actors: ", movie.actors),
                            style = MaterialTheme.typography.bodySmall
                        )

                        Text(
                            modifier = Modifier.padding(top = 4.dp),
                            text = annotatedString("Rating: ", movie.rating),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }

                Icon(
                    modifier = Modifier
                        .size(25.dp)
                        .align(Alignment.End)
                        .clickable {
                            expandedState.value = !expandedState.value
                        },
                    imageVector = if (expandedState.value) {
                        Icons.Default.KeyboardArrowUp
                    } else {
                        Icons.Default.KeyboardArrowDown
                    },
                    contentDescription = "Expand card arrow"
                )
            }
        }
    }
}

private fun annotatedString(title: String, description: String): AnnotatedString {
    return buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(title)
        }

        append(description)
    }
}

@Preview
@Composable
private fun DefaultPreview() {
    MovieRow(movie = MovieRepository().getMovies().first()) {

    }
}