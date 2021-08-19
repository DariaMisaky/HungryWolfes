package com.example.hungrywolfes.network

data class MealDetails(val meals: List<ListMealsDetails>)
data class ListMealsDetails(
    val idMeal: String,
    val strMeal: String,
    val strTags:String?,
    val strMealThumb:String,
    val strInstructions:String,
    val strIngredient1:String,
    val strIngredient2: String,
    val strIngredient3:String,
    val strMeasure1:String,
    val strMeasure2:String,
    val strMeasure3:String
)

//"idMeal":"52772",
//"strMeal":"Teriyaki Chicken Casserole"
//"strTags":"Meat,Casserole"
//"strInstruction":"Put some water in ......."
//"strIngredient1":"soy sauce"
//"strIngredient2":"water",
//"strIngredient3":"brown sugar",
//"strMeasure1":"3\/4 cup",
//"strMeasure2":"1\/2 cup",
//"strMeasure3":"1\/4 cup",