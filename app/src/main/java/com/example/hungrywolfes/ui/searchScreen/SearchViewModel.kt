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

class SearchViewModel : ViewModel() {
    private val _searchMeal = MutableLiveData<MealImages>()
    val searchMeal: LiveData<MealImages> = _searchMeal

    private val _onBackButton = SingleLiveEvent<Any>()
    val onBackButton = _onBackButton

    private val _receivedInfo = MutableLiveData<Boolean>(true)
    val receivedInfo: MutableLiveData<Boolean> = _receivedInfo

    fun getSearchedMeal(item: String) {
        viewModelScope.launch {
            try {
                _searchMeal.value = CategoryApi.retrofitService.searchFood(item)
                _receivedInfo.value = true
            } catch (e: java.lang.Exception) {
                _receivedInfo.value = false
            }
        }
    }

    fun navigateBack() {
        _onBackButton.call()
    }
}