package com.example.hungrywolfes.ui.detailsScreen

import android.util.Log
import androidx.lifecycle.*
import com.example.hungrywolfes.network.CategoryApi
import com.example.hungrywolfes.network.ListMealsDetails
import com.example.hungrywolfes.network.ListMealsImages
import com.example.hungrywolfs.SingleLiveEvent
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.launch

private const val keyHawk = "favoriteFood"

class DetailsViewModel : ViewModel() {

    private val _onBackButton = SingleLiveEvent<Any>()
    val onBackButton = _onBackButton

    private val _detailsMeal = MutableLiveData<ListMealsDetails>()
    val detailsMeal: LiveData<ListMealsDetails> = _detailsMeal

    val stringTags = _detailsMeal.map {
        it.strTags?.split(",")
    }

    val buttonCheckStatus: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    private val _favoriteMealList = MutableLiveData<MutableList<ListMealsImages>>()
    val favoriteMealList: MutableLiveData<MutableList<ListMealsImages>> = _favoriteMealList


    init {
        _favoriteMealList.value = Hawk.get(keyHawk) ?: mutableListOf()

    }

    fun navigateBack() {
        _onBackButton.call()
    }

    fun getDetailsMeal(item: Int) {
        viewModelScope.launch {
            try {
                _detailsMeal.value =
                    CategoryApi.retrofitService.detailsFood(item).meals.firstOrNull()
                buttonCheckStatus.value = itemInFavorite()
            } catch (e: java.lang.Exception) {
            }
        }
    }

    fun onFavoriteButton() {
        if (itemInFavorite()) {
            buttonCheckStatus.value = false
            removeMealFromFavorite()


        } else {
            addMealToFavorite()
            buttonCheckStatus.value = true
        }
    }

    private fun addMealToFavorite() {
        detailsMeal.value?.let { details ->
            _favoriteMealList.value?.add(
                ListMealsImages(
                    details.strMeal,
                    details.strMealThumb,
                    details.idMeal
                )
            )
        }
        Hawk.put(keyHawk, _favoriteMealList.value)
    }

    private fun removeMealFromFavorite() {
        detailsMeal.value?.let { details ->
            _favoriteMealList.value?.remove(
                ListMealsImages(
                    details.strMeal,
                    details.strMealThumb,
                    details.idMeal
                )
            )
        }
        Hawk.put(keyHawk, _favoriteMealList.value)
    }

    private fun itemInFavorite(): Boolean {
        return detailsMeal.value?.let { details ->
            favoriteMealList.value?.contains(
                ListMealsImages(
                    details.strMeal,
                    details.strMealThumb,
                    details.idMeal
                )
            )
        } ?: false
    }
}