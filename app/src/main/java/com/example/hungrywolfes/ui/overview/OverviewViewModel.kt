package com.example.hungrywolfes.ui.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolfes.network.*
import kotlinx.coroutines.launch

private const val TAG: String = "OverviewViewModel"
enum class Status{LOADING,ERROR,DONE}
class OverviewViewModel : ViewModel() {

    private val _mealCategory = MutableLiveData<MealCategory>()
    val mealCategory: LiveData<MealCategory> = _mealCategory

    private val _mealImages = MutableLiveData<MealImages>()
    var mealImages: LiveData<MealImages> = _mealImages

    private val _status= MutableLiveData<Status>()
    val status:LiveData<Status> =_status

    init {
        getMealCategory()

    }

    private fun getMealCategory() {
        viewModelScope.launch {
            try {
                CategoryApi.retrofitService.getCategory()?.let { categories ->
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
            _status.value=Status.LOADING
            try {
                _mealImages.value = CategoryApi.retrofitService.getFood(item.strCategory)
                _status.value=Status.DONE
                Log.d(TAG, "$item")
            } catch (e: Exception) {
                Log.d(TAG, "getFood error")
                _status.value=Status.ERROR
            }
        }


    }

    fun searchedMeal(item: String) {
        viewModelScope.launch {
            try {
                _searchMeal.value = CategoryApi.retrofitService.searchFood(item)
            } catch (e: java.lang.Exception) {

            }
        }
    }
}
