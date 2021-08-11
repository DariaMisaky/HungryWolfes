package com.example.hungrywolfes.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://www.themealdb.com"
private val moshi= Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit= Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MarsApiService {
    @GET("/api/json/v1/1/list.php?c=categories") //GET request  /photos (endpoint)
    suspend fun getCategory() :MealCategory //appends the endpoint photos to the base URL ?????????

}
object CategoryApi{ //object are used to declare singleton objects
    val retrofitService : MarsApiService by lazy { //lazy to make sure it is initialized at its first usage
        retrofit.create(MarsApiService::class.java)}
}