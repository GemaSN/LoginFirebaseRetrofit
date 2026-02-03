package com.example.loginfirebaseretrofit.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.loginfirebaseretrofit.R
import com.example.loginfirebaseretrofit.network.AnimeInfo
import com.google.firebase.auth.FirebaseAuth
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun HomeScreen(viewModel: HomeViewModel, auth: FirebaseAuth, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = Modifier.fillMaxSize().padding(top = 20.dp),
        topBar = { HomeTopBar(viewModel, auth) },
        content = {
            when (val animeUiState = viewModel.animeUiState) {
                is AnimeUiState.Success -> SuccessContent(modifier = modifier.fillMaxSize().padding(it),
                    animeUiState.anime)
                is AnimeUiState.Error -> ErrorContent(modifier = modifier.fillMaxSize().padding(it))
                is AnimeUiState.Loading -> LoadingContent(modifier = modifier.fillMaxSize().padding(it))
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(viewModel: HomeViewModel, auth: FirebaseAuth) {
    TopAppBar(
        title = { Text(text = "Lista de fotos de ${auth.currentUser?.email?.split('@')[0]}") },
        actions = {
            IconButton(onClick = { viewModel.getPhotos() },
                content = {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh")
                })
        }
    )
}

@Composable
fun ErrorContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = "Connection error"
        )
        Text(text = "Loading failed",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun LoadingContent(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = Modifier.size(64.dp))
    }
}

@Composable
fun SuccessContent(modifier: Modifier = Modifier, photos: List<AnimeInfo>) {
    LazyColumn(modifier = modifier) {
        item() { Text("Success: ${photos.size} Anime card retrieved") }
        items(photos.size) { AnimeCard(photos[it]) }
    }
}

@Composable
fun AnimeCard(anime: AnimeInfo) {
    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
    ){
        Row(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                .data(anime.imgSrc)
                .crossfade(true)
                .build(),
                contentDescription = "Anime photos",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = "Name: ${anime.title}",
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = "Episodes: ${anime.episodes}",
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = "Rating: ${anime.rating}",
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = "Score: ${anime.score}",
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}