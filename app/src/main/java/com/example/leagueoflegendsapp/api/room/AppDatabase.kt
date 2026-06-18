package com.example.leagueoflegendsapp.api.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.leagueoflegendsapp.model.BookmarkEntity
import com.example.leagueoflegendsapp.model.NewsEntity

@Database(
    entities = [BookmarkEntity::class, NewsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookmarkDao(): BookmarkDao
    abstract fun newsDao(): NewsDao
}
