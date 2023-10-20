package com.example.checkpoint2.data.model

import com.squareup.moshi.Json

data class GoogleRepo(
    val id: Long,
    @Json(name = "full_name") val fullName: String,
    val private: Boolean = false
)
