package com.example.hungrywolfes.ui.detailsScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolfes.network.CategoryApi
import com.example.hungrywolfes.network.ListMealsDetails
import com.example.hungrywolfes.network.MealDetails
import com.example.hungrywolfs.SingleLiveEvent
import kotlinx.coroutines.launch

private const val TAG = "DetailsViewModel"

class DetailsViewModel : ViewModel() {

    private val _onBackButton = SingleLiveEvent<Any>()
    val onBackButton = _onBackButton

    private val _detailsMeal = MutableLiveData<ListMealsDetails>()
    val detailsMeal: LiveData<ListMealsDetails> = _detailsMeal

    private val _stringTags=MutableLiveData<List<String>>()
    val stringTags:LiveData<List<String>> = _stringTags

    private val _addItemToFavorite=MutableLiveData<Boolean>()
    val addItemToFavorite:LiveData<Boolean> = _addItemToFavorite


    fun navigateBack() {
        _onBackButton.call()
    }

    fun getDetailsMeal(item: Int) {
        viewModelScope.launch {
            try {
                _detailsMeal.value =
                    CategoryApi.retrofitService.detailsFood(item).meals.firstOrNull()
               _stringTags.value= detailsMeal.value?.strTags?.split(",")

            } catch (e: java.lang.Exception) {
            }
        }
    }
    fun onFavoriteButton(){
        _addItemToFavorite.value=true
    }
}