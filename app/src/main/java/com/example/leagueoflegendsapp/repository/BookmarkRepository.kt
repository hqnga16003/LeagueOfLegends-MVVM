package com.example.leagueoflegendsapp.repository

import com.example.leagueoflegendsapp.api.room.BookmarkDao
import com.example.leagueoflegendsapp.model.Article
import com.example.leagueoflegendsapp.model.toBookmarkEntity
import com.example.leagueoflegendsapp.model.toDomain
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookmarkRepository @Inject constructor(
    private val dao: BookmarkDao
) {


    suspend fun saveArticle(
        article: Article
    ) {
        dao.insert(
            article.toBookmarkEntity()
        )
    }

    suspend fun removeArticle(
        article: Article
    ) {
        dao.delete(
            article.toBookmarkEntity()
        )
    }

    fun getBookmarks(): Flow<List<Article>> {
        return dao.observeBookmarks()
            .map { list ->
                list.map {
                    it.toDomain()
                }
            }
    }

    suspend fun isBookmarked(
        articleId: String
    ): Boolean {
        return dao.isBookmarked(articleId)
    }
}
