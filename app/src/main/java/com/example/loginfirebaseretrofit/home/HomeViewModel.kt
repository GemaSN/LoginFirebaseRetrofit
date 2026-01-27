package com.example.loginfirebaseretrofit.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginfirebaseretrofit.network.MarsApiService
import com.example.loginfirebaseretrofit.network.MarsPhoto
import kotlinx.coroutines.launch
import java.io.IOException


class HomeViewModel(private val apiService: MarsApiService): ViewModel() {
    var photosUiState: PhotosUiState by mutableStateOf(PhotosUiState.Loading)

    init { getPhotos() }

    fun getPhotos() {
        photosUiState = PhotosUiState.Loading
        viewModelScope.launch {
            photosUiState = try {
                PhotosUiState.Success(apiService.getPhotos())
            } catch (ex: IOException) {
                PhotosUiState.Error
            }
        }
    }
}

sealed interface PhotosUiState {
    data class Success(val photos: List<MarsPhoto>) : PhotosUiState
    object Error : PhotosUiState
    object Loading : PhotosUiState
}