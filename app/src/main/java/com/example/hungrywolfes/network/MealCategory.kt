package com.example.hungrywolfes.network
import com.squareup.moshi.Json

data class MealCategory (val meals:List<ListMealCategory>)
data class ListMealCategory(val strCategory: String)
