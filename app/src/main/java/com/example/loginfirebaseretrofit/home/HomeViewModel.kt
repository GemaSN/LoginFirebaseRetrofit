package com.example.loginfirebaseretrofit.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginfirebaseretrofit.network.LSApiService
import com.example.loginfirebaseretrofit.network.LSChar
import kotlinx.coroutines.launch
import java.io.IOException


class HomeViewModel(private val apiService: LSApiService): ViewModel() {
    var lsCharsUiState: LSCharsUiState by mutableStateOf(LSCharsUiState.Loading)

    init { getPhotos() }

    fun getPhotos() {
        lsCharsUiState = LSCharsUiState.Loading
        viewModelScope.launch {
            lsCharsUiState = try {
                LSCharsUiState.Success(apiService.getCharacters().results)
            } catch (ex: IOException) {
                LSCharsUiState.Error
            }
        }
    }
}

sealed interface LSCharsUiState {
    data class Success(val photos: List<LSChar>) : LSCharsUiState
    object Error : LSCharsUiState
    object Loading : LSCharsUiState
}