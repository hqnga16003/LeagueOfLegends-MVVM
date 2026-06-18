package com.example.leagueoflegendsapp.api.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.leagueoflegendsapp.model.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(
        bookmark: BookmarkEntity
    )

    @Delete
    suspend fun delete(
        bookmark: BookmarkEntity
    )

    @Query("SELECT * FROM bookmarks")
    fun observeBookmarks(): Flow<List<BookmarkEntity>>

    @Query(
        "SELECT EXISTS(SELECT 1 FROM bookmarks WHERE id = :id)"
    )
    suspend fun isBookmarked(
        id: String
    ): Boolean
}
