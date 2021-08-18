package com.example.hungrywolfes.ui.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolfes.network.*
import com.example.hungrywolfs.SingleLiveEvent
import kotlinx.coroutines.launch

private const val TAG = "HomeViewModel"

class OverviewViewModel : ViewModel() {

    private val _mealCategory = MutableLiveData<MealCategory>()
    val mealCategory: LiveData<MealCategory> = _mealCategory

    private val _mealImages = MutableLiveData<MealImages>()
    val mealImages: LiveData<MealImages> = _mealImages

    private val _goToSearch = SingleLiveEvent<Any>()
    val goToSearch = _goToSearch

    init {
        getMealCategory()
    }

    private fun getMealCategory() {
        viewModelScope.launch {
            try {
                CategoryApi.retrofitService.getCategory().let { categories ->
                    _mealCategory.value = categories
                    categories.meals.getOrNull(0)?.let { getCategoryMeals(it) }
                }
            } catch (e: Exception) {
                Log.e(TAG, e.message ?: "Error getMealCategory")
            }
        }
    }

    fun getCategoryMeals(item: ListMealCategory) {
        viewModelScope.launch {
            try {
                _mealImages.value = CategoryApi.retrofitService.getFood(item.strCategory)

                Log.d(TAG, "$item")
            } catch (e: Exception) {
                Log.e(TAG, "Error getCategoryMeals")
            }
        }
    }

    fun searchClicked() {
       _goToSearch.call()
    }

}
