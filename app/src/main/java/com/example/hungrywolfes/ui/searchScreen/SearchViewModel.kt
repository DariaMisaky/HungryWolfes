package com.example.hungrywolfes.ui.searchScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolfes.network.CategoryApi
import com.example.hungrywolfes.network.MealImages
import com.example.hungrywolfs.SingleLiveEvent
import kotlinx.coroutines.launch

private const val TAG = "SearchViewModel"
class SearchViewModel : ViewModel() {
    private val _searchMeal = MutableLiveData<MealImages>()
    val searchMeal: LiveData<MealImages> = _searchMeal

    private val _onBackButton = SingleLiveEvent<Any>()
    val onBackButton =_onBackButton

    fun getSearchedMeal(item: String) {
        viewModelScope.launch {
            try {
                _searchMeal.value = CategoryApi.retrofitService.searchFood(item)
            } catch (e: java.lang.Exception) {
            }
        }
    }
    fun navigateBack(){
        _onBackButton.call()
    }
}