package com.route.newsapp

import android.app.Application
import com.route.newsapp.data.database.MyDatabase
import com.route.newsapp.utils.Connectivity
import com.route.newsapp.utils.ConnectivityImpl
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}