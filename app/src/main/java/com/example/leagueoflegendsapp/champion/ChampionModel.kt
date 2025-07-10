package com.example.leagueoflegendsapp.champion


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChampionModel(
    @SerialName("id")
    val id: String,
    @SerialName("key")
    val key: String,
    @SerialName("name")
    val name: String,
    @SerialName("title")
    val title: String,
    @SerialName("blurb")
    val blurb: String,
    @SerialName("info")
    val info: InfoModel,
    @SerialName("image")
    val image: ImageModel,
    @SerialName("tags")
    val tags: List<String>,
    @SerialName("partype")
    val parType: String,
    @SerialName("stats")
    val stats: StatsModel,
)