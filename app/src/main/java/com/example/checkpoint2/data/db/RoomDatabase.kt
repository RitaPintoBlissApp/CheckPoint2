package com.example.checkpoint2.data.db

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase

@Database(entities = [EmojiEntity::class], version = 1, exportSchema = false)
//@Database(entities = [EmojiEntity::class, ... , ...])

abstract class RoomDatabase: RoomDatabase() {
    abstract fun emojiDao():EmojiDao

    //avatar dao
    //repos dao
}