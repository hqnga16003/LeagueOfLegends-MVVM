package com.example.leagueoflegendsapp.detail.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SkinModel(
    @SerialName("chromas")
    val chromas: Boolean,
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("num")
    val num: Int
)