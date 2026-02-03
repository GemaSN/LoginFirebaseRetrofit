package com.example.loginfirebaseretrofit.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL = "https://api.jikan.moe"

// Configuración Json para ignorar campos desconocidos
private val json = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
}

private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface AnimeApiService {
    @GET("/v4/anime")
    suspend fun getAnimes(): AnimeResponse
}

object MarsApi {
    val retrofitService: AnimeApiService by lazy {
        retrofit.create(AnimeApiService::class.java)
    }
}