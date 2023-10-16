package com.example.checkpoint2.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

//Create a Retrofit object with the base URL and the converter factory
val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
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