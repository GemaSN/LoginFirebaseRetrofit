package com.example.loginfirebaseretrofit.network

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class HPChar(
    val id: String,
    val name: String,
    @SerialName(value = "alternate_names")
    val alternateNames: List<String>,
    val species: String,
    val gender: String,
    val house: String,
    val dateOfBirth: String?,
    val yearOfBirth: Int?,
    val wizard: Boolean,
    val ancestry: String,
    val eyeColour: String,
    val hairColour: String,
    val wand: Wand,
    val patronus: String,
    val hogwartsStudent: Boolean,
    val hogwartsStaff: Boolean,
    val actor: String,
    @SerialName(value = "alternate_actors")
    val alternateActors: List<String>,
    val alive: Boolean,
    val image: String,
    val wandImage: String
)

@Serializable
data class Wand(
    val wood: String,
    val core: String,
    val length: Float?
)