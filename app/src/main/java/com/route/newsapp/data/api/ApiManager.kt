package com.route.newsapp.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    companion object{
        const val API_KEY = "ea313f6c2fed417196fe8fa577ac93df"
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun getWebServices(): WebServices = retrofit.create(WebServices::class.java)
    }
}