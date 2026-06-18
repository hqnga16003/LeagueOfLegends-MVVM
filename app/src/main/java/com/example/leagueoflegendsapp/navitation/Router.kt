package com.example.leagueoflegendsapp.navitation

import androidx.navigation3.runtime.NavKey
import com.example.leagueoflegendsapp.model.Article
import kotlinx.serialization.Serializable

@Serializable
data object NewsRouter : NavKey


@Serializable
data object BookmarksRouter : NavKey

@Serializable
data class NewDetailRouter(val article: Article) : NavKey