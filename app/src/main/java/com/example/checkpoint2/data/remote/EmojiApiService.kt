package com.example.checkpoint2.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

//defines how Retrofit talks to the web server using HTTP requests.
interface EmojiApiService {
    @GET("/emojis")
    suspend fun getEmojis(): Map<String, String>
}


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


//Create a Retrofit object with the base URL and the converter factory
val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl("https://api.github.com")
    .build()


object EmojiApi {
    val retrofitService : EmojiApiService by lazy {
        retrofit.create(EmojiApiService::class.java)
    }
}