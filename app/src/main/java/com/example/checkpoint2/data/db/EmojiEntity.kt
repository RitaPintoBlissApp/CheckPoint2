package com.example.checkpoint2.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "emoji")
data class EmojiEntity(
    @PrimaryKey val name: String,
    val url: String
)