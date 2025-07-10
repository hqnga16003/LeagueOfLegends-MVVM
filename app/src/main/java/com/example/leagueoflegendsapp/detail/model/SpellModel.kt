package com.example.leagueoflegendsapp.detail.model


import com.example.leagueoflegendsapp.champion.ImageModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpellModel(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("tooltip")
    val tooltip: String,
    @SerialName("leveltip")
    val levelTip: LevelTipModel,
    @SerialName("maxrank")
    val maxRank: Int,
    @SerialName("cooldown")
    val cooldown: List<Double>,
    @SerialName("cooldownBurn")
    val cooldownBurn: String,
    @SerialName("cost")
    val cost: List<Int>,
    @SerialName("costBurn")
    val costBurn: String,
    @SerialName("datavalues")
    val dataValues: DataValuesModel,
    @SerialName("effect")
    val effect: List<List<Double>?>,
    @SerialName("effectBurn")
    val effectBurn: List<String?>,
    @SerialName("vars")
    val vars: List<String?>,
    @SerialName("costType")
    val costType: String,
    @SerialName("maxammo")
    val maxAmmo: String,
    @SerialName("range")
    val range: List<Int>,
    @SerialName("rangeBurn")
    val rangeBurn: String,
    @SerialName("image")
    val image: ImageModel,
    @SerialName("resource")
    val resource: String,
)