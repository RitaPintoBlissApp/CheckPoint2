package com.example.checkpoint2.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//Create a Retrofit object with the base URL and the converter factory
val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl("https://api.github.com")
    .build()


//defines how Retrofit talks to the web server using HTTP requests.
interface GithubApiService {
    @GET("/emojis")
    suspend fun getEmojis(): Map<String, String>
}

object GithubApi {
    val retrofitService : GithubApiService by lazy {
        retrofit.create(GithubApiService::class.java)
    }
}