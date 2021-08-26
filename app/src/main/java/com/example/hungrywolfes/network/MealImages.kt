package com.example.hungrywolfes.network

data class MealImages(val meals: List<ListMealsImages>)
data class ListMealsImages(
    val strMeal: String,
    val strMealThumb: String,
    val idMeal: String
)