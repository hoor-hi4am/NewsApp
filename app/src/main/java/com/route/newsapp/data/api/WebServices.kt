package com.route.newsapp.data.api

import com.route.newsapp.data.api.model.ArticlesResponse
import com.route.newsapp.data.api.model.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("https://newsapi.org/v2/top-headlines/sources")
    suspend fun getSources(@Query("apiKey")apiKey: String = ApiManager.API_KEY,
                   @Query("category")category: String
    ): SourcesResponse
    @GET("https://newsapi.org/v2/everything")
    suspend fun getArticles(@Query("apiKey")apiKey: String = ApiManager.API_KEY,
                    @Query("sources")source: String
    ): ArticlesResponse

    @GET("https://newsapi.org/v2/everything")
    suspend fun searchArticles(@Query("apiKey")apiKey: String = ApiManager.API_KEY,
                    @Query("sources")source: String,
                    @Query("q")query: String
    ): ArticlesResponse
}
