package com.example.checkpoint2.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//import kotlinx.serialization.Serializable

//@Serializable

data class Emoji(
    val name: String,
    @Json(name = "url") val imgSrc : String

)