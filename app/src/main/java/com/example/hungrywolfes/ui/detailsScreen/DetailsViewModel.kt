package com.example.hungrywolfes.ui.detailsScreen

import android.util.Log
import androidx.lifecycle.*
import com.example.hungrywolfes.R
import com.example.hungrywolfes.network.CategoryApi
import com.example.hungrywolfes.network.ListMealsDetails
import com.example.hungrywolfes.network.ListMealsImages
import com.example.hungrywolfes.network.MealDetails
import com.example.hungrywolfs.SingleLiveEvent
import kotlinx.coroutines.launch

private const val TAG = "DetailsViewModel"

class DetailsViewModel : ViewModel() {

    private val _onBackButton = SingleLiveEvent<Any>()
    val onBackButton = _onBackButton

    private val _detailsMeal = MutableLiveData<ListMealsDetails>()
    val detailsMeal: LiveData<ListMealsDetails> = _detailsMeal

    val stringTags = _detailsMeal.map {
        it.strTags?.split(",")
    }

    private val _addItemToFavorite = MutableLiveData<Boolean>()
    val addItemToFavorite: LiveData<Boolean> = _addItemToFavorite


    fun navigateBack() {
        _onBackButton.call()
    }

    fun getDetailsMeal(item: Int) {
        viewModelScope.launch {
            try {
                _detailsMeal.value =
                    CategoryApi.retrofitService.detailsFood(item).meals.firstOrNull()
            } catch (e: java.lang.Exception) {
            }
        }
    }

    fun onFavoriteButton() {
        _addItemToFavorite.value = true
    }
}