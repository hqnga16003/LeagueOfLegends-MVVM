package com.example.leagueoflegendsapp.model

import kotlinx.serialization.Serializable

@Serializable

data class NewsResponseDto(
    val status: String?,
    val totalResults: Int?,
    val articles: List<ArticleDto>?
)

@Serializable

data class SourceDto(
    val id: String?,
    val name: String?
)
@Serializable

data class ArticleDto(
    val source: SourceDto?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)

fun ArticleDto.toDomain(): Article {
    return Article(
        id = url.orEmpty(),
        source = source?.name.orEmpty(),
        author = author.orEmpty(),
        title = title.orEmpty(),
        description = description.orEmpty(),
        url = url.orEmpty(),
        imageUrl = urlToImage.orEmpty(),
        publishedAt = publishedAt.orEmpty(),
        content = content.orEmpty()
    )
}