package com.example.hungrywolfes.ui.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolfes.network.*
import kotlinx.coroutines.launch

private const val TAG: String = "OverviewViewModel"

class OverviewViewModel : ViewModel() {

    private val _mealCategory = MutableLiveData<MealCategory>()
    val mealCategory: LiveData<MealCategory> = _mealCategory

    private val _mealImages = MutableLiveData<MealImages>()
    var mealImages: LiveData<MealImages> = _mealImages

    init {
        getMealCategory()

    }

    private fun getMealCategory() {
        viewModelScope.launch {
            try {
                _mealCategory.value = CategoryApi.retrofitService.getCategory()
            } catch (e: Exception) {
                Log.d(TAG, e.message ?: "Error")
            }
        }
    }

    fun mealSelected(item: ListMealCategory) {
        viewModelScope.launch {
            try {

                _mealImages.value = CategoryApi.retrofitService.getFood(item.strCategory)

            } catch (e: Exception) {
                Log.d(TAG, "getFood error")
            }
        }


    }




}
