package com.example.checkpoint2.data.remote

import com.example.checkpoint2.data.model.Avatar
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface AvatarApiService{
    @GET("/users")
    //suspend fun getAvatar(): List<Avatar>
    suspend fun getAvatar(): List<Map<String,Any>>
    suspend fun searchAvatar(nome: String): List<Map<String,Any>>
}

private val moshiAvatar = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofitAvatar = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshiAvatar))
    .baseUrl("https://api.github.com")
    .build()

object AvatarApi{
    val retrofitAvatarService: AvatarApiService by lazy {
        retrofitAvatar.create(AvatarApiService::class.java)
    }
}

