package com.example.loginfirebaseretrofit.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginfirebaseretrofit.network.AnimeApiService
import com.example.loginfirebaseretrofit.network.AnimeInfo
import com.example.loginfirebaseretrofit.network.AnimeData
import kotlinx.coroutines.launch
import java.io.IOException

class HomeViewModel(private val apiService: AnimeApiService): ViewModel() {
    var animeUiState: AnimeUiState by mutableStateOf(AnimeUiState.Loading)

    init { getPhotos() }

    fun getPhotos() {
        animeUiState = AnimeUiState.Loading
        viewModelScope.launch {
            animeUiState = try {
                val response = apiService.getAnimes()
                // Convertir de AnimeData a AnimeInfo
                val animeList = response.data.map { it.toAnimeInfo() }
                AnimeUiState.Success(animeList)
            } catch (ex: IOException) {
                AnimeUiState.Error
            } catch (ex: Exception) {
                AnimeUiState.Error
            }
        }
    }
}

// Función de extensión para convertir AnimeData a AnimeInfo
private fun AnimeData.toAnimeInfo() = AnimeInfo(
    mal_id = this.mal_id.toString(),
    imgSrc = this.images.jpg.image_url,
    title = this.title,
    episodes = this.episodes ?: 0,
    rating = this.rating ?: "N/A",
    score = this.score?.toFloat() ?: 0f
)

sealed interface AnimeUiState {
    data class Success(val anime: List<AnimeInfo>) : AnimeUiState
    object Error : AnimeUiState
    object Loading : AnimeUiState
}