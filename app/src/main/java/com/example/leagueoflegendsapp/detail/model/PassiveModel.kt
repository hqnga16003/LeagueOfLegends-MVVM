package com.example.leagueoflegendsapp.detail.model


import com.example.leagueoflegendsapp.champion.ImageModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PassiveModel(
    @SerialName("description")
    val description: String,
    @SerialName("image")
    val image: ImageModel,
    @SerialName("name")
    val name: String
)