package com.example.leagueoflegendsapp.detail.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LevelTipModel(
    @SerialName("effect")
    val effect: List<String>,
    @SerialName("label")
    val label: List<String>
)