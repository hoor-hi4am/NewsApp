package com.route.newsapp

import android.app.Application
import com.route.newsapp.data.database.MyDatabase
import com.route.newsapp.utils.Connectivity

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MyDatabase.createDatabase(this)
        Connectivity.context = this
    }
}