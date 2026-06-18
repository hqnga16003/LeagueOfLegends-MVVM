package com.example.leagueoflegendsapp.hilt

import android.content.Context
import androidx.room.Room
import com.example.leagueoflegendsapp.api.NewsApiService
import com.example.leagueoflegendsapp.api.RetrofitInstance
import com.example.leagueoflegendsapp.api.room.AppDatabase
import com.example.leagueoflegendsapp.api.room.BookmarkDao
import com.example.leagueoflegendsapp.api.room.NewsDao
import com.example.leagueoflegendsapp.repository.BookmarkRepository
import com.example.leagueoflegendsapp.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "news_digest.db"
        ).build()
    }

    @Provides
    fun provideBookmarkDao(
        database: AppDatabase
    ): BookmarkDao {
        return database.bookmarkDao()
    }

    @Provides
    fun provideNewsDao(
        database: AppDatabase
    ): NewsDao {
        return database.newsDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideNewsApiService(): NewsApiService {
        return RetrofitInstance.api
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideBookmarkRepository(
        dao: BookmarkDao
    ): BookmarkRepository {
        return BookmarkRepository(dao)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        api: NewsApiService,
        newsDao: NewsDao
    ): NewsRepository {
        return NewsRepository(api, newsDao)
    }
}
