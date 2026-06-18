package com.example.leagueoflegendsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val id: String,
    val source: String,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val imageUrl: String,
    val publishedAt: String,
    val content: String
)

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey
    val id: String,
    val source: String,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val imageUrl: String,
    val publishedAt: String,
    val content: String
)

@Entity(tableName = "news_cache")
data class NewsEntity(
    @PrimaryKey
    val id: String,
    val source: String,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val imageUrl: String,
    val publishedAt: String,
    val content: String
)

fun Article.toBookmarkEntity(): BookmarkEntity {
    return BookmarkEntity(
        id = id,
        source = source,
        author = author,
        title = title,
        description = description,
        url = url,
        imageUrl = imageUrl,
        publishedAt = publishedAt,
        content = content
    )
}

fun Article.toNewsEntity(): NewsEntity {
    return NewsEntity(
        id = id,
        source = source,
        author = author,
        title = title,
        description = description,
        url = url,
        imageUrl = imageUrl,
        publishedAt = publishedAt,
        content = content
    )
}

fun BookmarkEntity.toDomain() = Article(
    id = id,
    source = source,
    author = author,
    title = title,
    description = description,
    url = url,
    imageUrl = imageUrl,
    publishedAt = publishedAt,
    content = content
)

fun NewsEntity.toDomain() = Article(
    id = id,
    source = source,
    author = author,
    title = title,
    description = description,
    url = url,
    imageUrl = imageUrl,
    publishedAt = publishedAt,
    content = content
)
