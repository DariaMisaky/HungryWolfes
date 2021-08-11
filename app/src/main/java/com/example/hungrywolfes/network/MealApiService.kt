package com.example.hungrywolfes.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://www.themealdb.com"
private val retrofit= Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface MarsApiService {
    @GET("/api/json/v1/1/list.php?c=categories") //GET request  /photos (endpoint)
    suspend fun getCategory() :String //appends the endpoint photos to the base URL ?????????

}
object categoryApi{ //object are used to declare singleton objects
    val retrofitService : MarsApiService by lazy { //lazy to make sure it is initialized at its first usage
        retrofit.create(MarsApiService::class.java)}
}