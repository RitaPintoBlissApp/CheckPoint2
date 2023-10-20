package com.example.checkpoint2.data.model

import com.squareup.moshi.Json

data class Avatar(
    @Json(name = "login") val name: String,
    val id: Double,
    @Json(name = "avatar_url") val avatarSrc: String
)
