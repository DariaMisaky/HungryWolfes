package com.example.hungrywolfes.application

import android.app.Application
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.orhanobut.hawk.Hawk


class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Hawk.init(applicationContext).build()
    }
}