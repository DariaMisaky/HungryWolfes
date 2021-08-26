package com.example.hungrywolfes.ui.favoritesScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hungrywolfes.Constants
import com.example.hungrywolfes.network.ListMealsImages
import com.orhanobut.hawk.Hawk

class FavoriteViewModel : ViewModel() {
    private val _favoriteMealList = MutableLiveData<MutableList<ListMealsImages>>()
    val favoriteMealList: MutableLiveData<MutableList<ListMealsImages>> = _favoriteMealList

    fun onRemoveItem(listMeal: ListMealsImages) {
        _favoriteMealList.value?.remove(listMeal)
        Hawk.put(Constants.KEY_HAWK, _favoriteMealList.value)
    }

    fun refresh() {
        _favoriteMealList.value = Hawk.get(Constants.KEY_HAWK)
    }
}