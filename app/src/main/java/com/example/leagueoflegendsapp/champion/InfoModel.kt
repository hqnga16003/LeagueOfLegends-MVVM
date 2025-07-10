package com.example.leagueoflegendsapp.champion


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InfoModel(
    @SerialName("attack")
    val attack: Int,
    @SerialName("defense")
    val defense: Int,
    @SerialName("difficulty")
    val difficulty: Int,
    @SerialName("magic")
    val magic: Int
)