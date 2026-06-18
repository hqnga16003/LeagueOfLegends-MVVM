package com.example.leagueoflegendsapp.api.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.leagueoflegendsapp.model.NewsEntity

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<NewsEntity>)

    @Query("SELECT * FROM news_cache")
    suspend fun getAllArticles(): List<NewsEntity>

    @Query("DELETE FROM news_cache")
    suspend fun clearAllArticles()
}
