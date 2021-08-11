package com.example.hungrywolfes.network
import com.squareup.moshi.Json

data class MealCategory (
    @Json(name="strCategory") val strCategoryUrl:String
)