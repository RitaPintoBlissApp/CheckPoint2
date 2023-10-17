package com.example.checkpoint2.data.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface EmojiDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(emojis: List<EmojiEntity>)

    @Query("SELECT * FROM emoji")
    suspend fun getAllEmojis(): List<EmojiEntity>
}