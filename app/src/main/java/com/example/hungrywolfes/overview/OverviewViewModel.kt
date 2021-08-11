package com.example.hungrywolfes.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolfes.network.MealCategory
import com.example.hungrywolfes.network.CategoryApi
import kotlinx.coroutines.launch
import java.lang.Exception

class OverviewViewModel : ViewModel() {

    private val _mealCategory = MutableLiveData<MealCategory>()
    val mealCategory: LiveData<MealCategory> = _mealCategory

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status


    init {
        getMealCategory()
    }

    private fun getMealCategory() {
        val categoryList= mutableListOf<String>()
        viewModelScope.launch {
            try {
                _mealCategory.value = CategoryApi.retrofitService.getCategory()
                _status.value = "   First Mars image URL : ${_mealCategory.value!!.meals}"
                val meals_data=mealCategory.value!!.meals

                categoryList.add(meals_data[0].strCategory)

              //  Log.d("AGG", "${mealCategory.value!!.meals.lastIndex}} ")
                Log.d("AGG", "${meals_data[0].strCategory}    ")
            } catch (e: Exception) {
                Log.d("AGG", "getMealCategory error")
            }
        }
    }



}
