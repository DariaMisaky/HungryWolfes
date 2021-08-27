package com.example.hungrywolfes.network

data class MealCategory(val meals: List<ListMealCategory>)
data class ListMealCategory(val strCategory: String)