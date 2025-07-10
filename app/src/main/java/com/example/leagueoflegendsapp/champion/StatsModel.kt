package com.example.leagueoflegendsapp.champion


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatsModel(
    @SerialName("hp") val hp: Int,
    @SerialName("hpperlevel") val hpperLevel: Int,
    @SerialName("mp") val mp: Int,
    @SerialName("mpperlevel") val mpperLevel: Double,
    @SerialName("movespeed") val moveSpeed: Int,
    @SerialName("armor") val armor: Int,
    @SerialName("armorperlevel") val armorperLevel: Double,
    @SerialName("spellblock") val spellBlock: Int,
    @SerialName("spellblockperlevel") val spellBlockperLevel: Double,
    @SerialName("attackrange") val attackRange: Int,
    @SerialName("hpregen") val hpRegen: Double,
    @SerialName("hpregenperlevel") val hpRegenperLevel: Double,
    @SerialName("mpregen") val mpRegen: Double,
    @SerialName("mpregenperlevel") val mpRegenperLevel: Double,
    @SerialName("crit") val crit: Int,
    @SerialName("critperlevel") val critPerLevel: Int,
    @SerialName("attackdamage") val attackDamage: Int,
    @SerialName("attackdamageperlevel") val attackDamagePerLevel: Double,
    @SerialName("attackspeed") val attackSpeed: Double,
    @SerialName("attackspeedperlevel") val attackSpeedperLevel: Double,
)