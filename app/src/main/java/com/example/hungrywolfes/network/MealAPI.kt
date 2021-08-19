package com.example.hungrywolfes.network

import retrofit2.http.GET
import retrofit2.http.Query

interface MealAPI {

    @GET("v1/1/list.php?c=categories")
    suspend fun getCategory(): MealCategory

    @GET("v1/1/filter.php")
    suspend fun getFood(
        @Query("c") filter: String
    ): MealImages

    @GET("v1/1/search.php")
    suspend fun searchFood(
        @Query("s") filter: String
    ):MealImages

    @GET("v1/1/lookup.php")
    suspend fun detailsFood(
        @Query("i") filter : Int):MealDetails
}