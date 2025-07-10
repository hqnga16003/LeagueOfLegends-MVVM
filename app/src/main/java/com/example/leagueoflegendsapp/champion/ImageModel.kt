package com.example.leagueoflegendsapp.champion


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageModel(
    @SerialName("full")
    val full: String,
    @SerialName("group")
    val group: String,
    @SerialName("h")
    val h: Int,
    @SerialName("sprite")
    val sprite: String,
    @SerialName("w")
    val w: Int,
    @SerialName("x")
    val x: Int,
    @SerialName("y")
    val y: Int
)