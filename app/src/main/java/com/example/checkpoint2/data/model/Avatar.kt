package com.example.checkpoint2.data.model

import com.squareup.moshi.Json

data class Avatar(
    val login: String,
    val id: Int,
    @field:Json(name = "avatar_url") val avatarSrc: String
)
