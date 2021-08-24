package com.example.hungrywolfes.ui.profileScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hungrywolfes.network.UserProfile
import com.example.hungrywolfs.SingleLiveEvent

class UserViewModel : ViewModel() {

    private val _onBackButton = SingleLiveEvent<Any>()
    val onBackButton = _onBackButton

    private val _onFavoriteButton = SingleLiveEvent<Any>()
    val onFavoriteButton = _onFavoriteButton

    private val _onTermsAndConditionsButton = SingleLiveEvent<Any>()
    val onTermsAndConditions = _onTermsAndConditionsButton

    private val _userData = MutableLiveData<UserProfile>()
    val userData: LiveData<UserProfile> = _userData

    init {
        getUserProfile()
    }

    private fun getUserProfile() {
        _userData.value =
            UserProfile("Bradescu Daria", "daria_bradescu@yahoo.com", "+40 746 789 762")
    }

    fun navigateBack() {
        _onBackButton.call()
    }

    fun navigateToFragments() {
        _onFavoriteButton.call()
    }

    fun navigateToTermsAndConditions() {
        _onTermsAndConditionsButton.call()
    }
}