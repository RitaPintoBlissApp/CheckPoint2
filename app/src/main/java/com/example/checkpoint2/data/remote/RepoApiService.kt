package com.example.checkpoint2.data.remote

import android.graphics.pdf.PdfDocument.Page
import com.example.checkpoint2.data.model.GoogleRepo
import com.example.checkpoint2.data.model.ResponseApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ReposApiService {
    @GET("/users/{username}/repos")
    suspend fun getGoogleRepo(
        @Query("page") page:Int
    ): Response< ResponseApi >
//List<GoogleRepo>
}

private val moshiGR = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val GoogleRretrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshiGR))
    .baseUrl("https://api.github.com")
    .build()

 class GoogleRApi() {
    val retrofitGoogleRepoService: ReposApiService by lazy {
        GoogleRretrofit.create(ReposApiService::class.java)
    }
     suspend fun getGoogleRepo(page:Int): Response< ResponseApi > {
         return retrofitGoogleRepoService.getGoogleRepo(page)
     }
}

