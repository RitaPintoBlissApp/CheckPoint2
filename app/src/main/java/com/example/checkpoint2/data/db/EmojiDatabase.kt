package com.example.checkpoint2.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EmojiEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun emojiDao(): EmojiDao
}