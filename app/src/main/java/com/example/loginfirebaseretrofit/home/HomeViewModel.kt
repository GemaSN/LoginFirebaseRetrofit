package com.example.loginfirebaseretrofit.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginfirebaseretrofit.network.HPApiService
import com.example.loginfirebaseretrofit.network.HPChar
import kotlinx.coroutines.launch
import java.io.IOException


class HomeViewModel(private val apiService: HPApiService): ViewModel() {
    var hpCharsUiState: HPCharsUiState by mutableStateOf(HPCharsUiState.Loading)

    init { getPhotos() }

    fun getPhotos() {
        hpCharsUiState = HPCharsUiState.Loading
        viewModelScope.launch {
            hpCharsUiState = try {
                HPCharsUiState.Success(apiService.getCharacters())
            } catch (ex: IOException) {
                HPCharsUiState.Error
            }
        }
    }
}

sealed interface HPCharsUiState {
    data class Success(val photos: List<HPChar>) : HPCharsUiState
    object Error : HPCharsUiState
    object Loading : HPCharsUiState
}