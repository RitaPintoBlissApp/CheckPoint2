package com.example.checkpoint2.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface ReposApiService {
    @GET("users/{username}/repos")
    suspend fun getGoogleRepo(): Map<String, String>
}

private val moshiGR = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val GoogleRretrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshiGR))
    .baseUrl("https://api.github.com/")
    .build()
object GoogleRApi {
    val retrofitGoogleRepoService: ReposApiService by lazy {
        GoogleRretrofit.create(ReposApiService::class.java)
    }
}
