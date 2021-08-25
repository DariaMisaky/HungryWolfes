package com.example.hungrywolfes.ui.favoritesScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hungrywolfes.network.ListMealsImages
import com.orhanobut.hawk.Hawk

private const val keyHawk = "favoriteFood"

class FavoriteViewModel : ViewModel() {
    private val _favoriteMealList = MutableLiveData<MutableList<ListMealsImages>>()
    val favoriteMealList: MutableLiveData<MutableList<ListMealsImages>> = _favoriteMealList

    fun onRemoveItem(listMeal: ListMealsImages) {
        val currentList = Hawk.get<List<ListMealsImages>>(keyHawk).toMutableList()
        currentList.remove(listMeal)
        Hawk.put(keyHawk, currentList)
    }

    fun refresh() {
        _favoriteMealList.value = Hawk.get(keyHawk)
    }
}