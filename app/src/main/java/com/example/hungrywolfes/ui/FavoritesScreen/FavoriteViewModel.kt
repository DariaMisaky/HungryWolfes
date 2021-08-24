package com.example.hungrywolfes.ui.FavoritesScreen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hungrywolfes.network.ListMealsImages
import com.orhanobut.hawk.Hawk

private const val TAG = "FavoriteViewModel"
class FavoriteViewModel: ViewModel() {
    private val _favoriteMealList= MutableLiveData<MutableList<ListMealsImages>>()
    val favoriteMealList: MutableLiveData<MutableList<ListMealsImages>> = _favoriteMealList

    init{
      
        _favoriteMealList.value= Hawk.get("favoriteFood")
        Log.d(TAG, "am primit:${_favoriteMealList.value} ")
    }
}