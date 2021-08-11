package com.example.hungrywolfes.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolfes.network.categoryApi
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {

   private val _mealCategory=MutableLiveData<String>()
    val mealCategory:LiveData<String> =_mealCategory

    init{
        getMealCategory()
    }
    private fun getMealCategory(){
        viewModelScope.launch{
            _mealCategory.value=categoryApi.retrofitService.getCategory()
        }
    }

}
