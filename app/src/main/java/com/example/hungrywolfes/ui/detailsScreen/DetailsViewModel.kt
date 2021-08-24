package com.example.hungrywolfes.ui.detailsScreen

import android.util.Log
import androidx.lifecycle.*
import com.example.hungrywolfes.R
import com.example.hungrywolfes.network.CategoryApi
import com.example.hungrywolfes.network.ListMealsDetails
import com.example.hungrywolfes.network.ListMealsImages
import com.example.hungrywolfes.network.MealDetails
import com.example.hungrywolfs.SingleLiveEvent
import com.orhanobut.hawk.Hawk
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


    private val _addItemToFavoriteButton = MutableLiveData<Boolean>()
    val addItemToFavoriteButton: LiveData<Boolean> = _addItemToFavoriteButton

    private val _favoriteMealList = MutableLiveData<MutableList<ListMealsImages>>()
    val favoriteMealList: MutableLiveData<MutableList<ListMealsImages>> = _favoriteMealList

    private val _itemAlreadyInFavorites = MutableLiveData<Boolean>()
    val itemAlreadyInFavorites: LiveData<Boolean> = _itemAlreadyInFavorites


    init {
        _favoriteMealList.value = Hawk.get("favoriteFood") ?: mutableListOf()
    }

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
        _addItemToFavoriteButton.value = addItemToFavoriteButton.value == false
    }


    fun addMealToFavorite() {

        val idMeal = detailsMeal.value?.idMeal!!
        val nameMeal = detailsMeal.value?.strMeal!!
        val imageMeal = detailsMeal.value?.strMealThumb!!
        _favoriteMealList.value?.add(ListMealsImages(imageMeal, nameMeal, idMeal))
        Log.d(TAG, "addMealToFavoritee favoriteMealList: ${_favoriteMealList.value}")
        Hawk.put("favoriteFood", _favoriteMealList.value)
        if(_favoriteMealList.value?.contains(ListMealsImages(idMeal,nameMeal,imageMeal)) == true)
        {
            _itemAlreadyInFavorites.value=true
        }
    }

    fun removeMealFromFavorite() {
        val idMeal = detailsMeal.value?.idMeal!!
        val nameMeal = detailsMeal.value?.strMeal!!
        val imageMeal = detailsMeal.value?.strMealThumb!!
        _favoriteMealList.value?.remove(ListMealsImages(imageMeal, nameMeal, idMeal))

        if(_favoriteMealList.value?.contains(ListMealsImages(idMeal,nameMeal,imageMeal)) == true)
        {
            _itemAlreadyInFavorites.value=false
        }

        Hawk.put("favoriteFood", _favoriteMealList.value)
        Log.d(TAG, "removeMealFromFavorite: am dat remove ${_favoriteMealList.value}")
    }

}
