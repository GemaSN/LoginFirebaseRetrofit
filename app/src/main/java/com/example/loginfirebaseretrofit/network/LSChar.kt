package com.example.loginfirebaseretrofit.network

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class LSInfoChar(
    val count: Int,
    val next: String?,
    val prev: String? = null,
    val pages: Int,
    val results: List<LSChar>
)

@Serializable
data class LSChar(
    val id: Int,
    val age: Int? = null,                              // Ahora es opcional
    val birthdate: String? = null,                     // Ahora es opcional
    val description: String? = null,                   // Ahora es opcional
    val first_appearance_ep_id: Int? = null,           // Ahora es opcional
    val first_appearance_sh_id: Int? = null,           // Ahora es opcional
    val gender: String? = null,                        // Ahora es opcional
    val name: String,
    val occupation: String? = null,                    // Ahora es opcional
    val phrases: List<String>? = null,                 // Ahora es opcional
    val status: String? = null,                        // Ahora es opcional
    val first_appearance_ep: fisrtAppearance? = null,  // Ahora es opcional
    val first_appearance_sh: fisrtAppearance? = null,  // Ahora es opcional
)

@Serializable
data class fisrtAppearance(
    val id: Int,
    val airdate: String,
    val description: String,
    val episode_numbre: Int,
    val image_path: String,
    val name: String,
    val season: Int,
    val synopsis: String,
)
