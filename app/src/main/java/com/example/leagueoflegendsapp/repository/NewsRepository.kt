package com.example.leagueoflegendsapp.repository

import android.util.Log
import com.example.leagueoflegendsapp.api.NewsApiService
import com.example.leagueoflegendsapp.api.room.NewsDao
import com.example.leagueoflegendsapp.model.Article
import com.example.leagueoflegendsapp.model.toDomain
import com.example.leagueoflegendsapp.model.toNewsEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val api: NewsApiService,
    private val newsDao: NewsDao
) {
    suspend fun getArticles(): List<Article> {
        return try {
            val response = api.getNews()
            val articles = response.articles?.map { it.toDomain() } ?: emptyList()
            
            if (articles.isNotEmpty()) {
                newsDao.clearAllArticles()
                newsDao.insertArticles(articles.map { it.toNewsEntity() })
            }
            articles
        } catch (e: Exception) {
            val cachedArticles = newsDao.getAllArticles()
            if (cachedArticles.isNotEmpty()) {
                cachedArticles.map { it.toDomain() }
            } else {
                emptyList()
            }
        }
    }
}
