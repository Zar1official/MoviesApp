package ru.zar1official.moviesapp.presentation.components

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.zar1official.moviesapp.R
import ru.zar1official.moviesapp.data.models.MovieEntity

@Composable
fun MovieItem(movieEntity: MovieEntity, context: Context) {
    Card(modifier = Modifier.fillMaxWidth(), elevation = 1.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight(),
                model = ImageRequest.Builder(context)
                    .data(movieEntity.image.link)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null
            )
            Spacer(modifier = Modifier.weight(0.1f))
            Text(text = movieEntity.title, modifier = Modifier.weight(2.5f))
            Spacer(modifier = Modifier.weight(0.5f))
            Text(text = movieEntity.description, modifier = Modifier.weight(7f))
        }
    }
}