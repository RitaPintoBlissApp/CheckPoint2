package com.example.checkpoint2.data.remote

import com.example.checkpoint2.data.model.GoogleRepo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface ReposApiService {
    @GET("/users/google/repos")
    suspend fun getGoogleRepo(): List<GoogleRepo>

}

private val moshiGR = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//val client= OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
//level = HttpLoggingInterceptor.Level.BODY
// }).build()

val GoogleRretrofit = Retrofit.Builder()
    .baseUrl("https://api.github.com")
    .addConverterFactory(MoshiConverterFactory.create(moshiGR))
    .build()


object GoogleRApi {
    val GoogleRepoService: ReposApiService by lazy {
        GoogleRretrofit.create(ReposApiService::class.java)
    }
 }

