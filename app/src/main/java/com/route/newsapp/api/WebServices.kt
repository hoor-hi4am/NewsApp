package com.route.newsapp.api

import com.route.newsapp.api.model.ArticlesResponse
import com.route.newsapp.api.model.SourceDM
import com.route.newsapp.api.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("https://newsapi.org/v2/top-headlines/sources")
    fun getSources(@Query("apiKey")apiKey: String = ApiManager.API_KEY,
                   @Query("category")category: String
    ): Call<SourcesResponse>
    @GET("https://newsapi.org/v2/everything")
    fun getArticles(@Query("apiKey")apiKey: String = ApiManager.API_KEY,
                    @Query("sources")source: String
    ): Call<ArticlesResponse>

    @GET("https://newsapi.org/v2/everything")
    fun searchArticles(@Query("apiKey")apiKey: String = ApiManager.API_KEY,
                    @Query("sources")source: String,
                    @Query("q")query: String
    ): Call<ArticlesResponse>
}
