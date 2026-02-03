package com.example.loginfirebaseretrofit.network

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

// Clase contenedora para la respuesta completa de la API
@Serializable
data class AnimeResponse(
    val data: List<AnimeData>
)

// Datos de cada anime
@Serializable
data class AnimeData(
    val mal_id: Int,

    val images: AnimeImages,

    val title: String,

    val episodes: Int?,

    val rating: String?,

    val score: Double?
)

// Estructura de imágenes
@Serializable
data class AnimeImages(
    val jpg: AnimeImageUrls
)

@Serializable
data class AnimeImageUrls(
    val image_url: String,
    val small_image_url: String?,
    val large_image_url: String?
)

// Mantiene la clase AnimeInfo para la UI (adaptador)
data class AnimeInfo(
    val mal_id: String,
    val imgSrc: String,
    val title: String,
    val episodes: Int,
    val rating: String,
    val score: Float
)