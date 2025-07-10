package com.example.leagueoflegendsapp.detail.model


import ChampionDetailModel
import com.example.leagueoflegendsapp.champion.ChampionModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChampionDetailResponseModel(
    @SerialName("data") val champion: Map<String, ChampionDetailModel> = emptyMap(),
    @SerialName("format") val format: String,
    @SerialName("type") val type: String,
    @SerialName("version") val version: String

)
fun Map<String, ChampionDetailModel>.toChampionDetailList(): List<ChampionDetailModel> = this.values.toList()


