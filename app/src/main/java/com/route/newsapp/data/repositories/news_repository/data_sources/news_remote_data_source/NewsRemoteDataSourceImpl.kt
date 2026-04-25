package com.route.newsapp.data.repositories.news_repository.data_sources.news_remote_data_source

import com.route.newsapp.data.api.ApiManager
import com.route.newsapp.data.api.WebServices
import com.route.newsapp.data.api.model.SourcesResponse
import jakarta.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(var services : WebServices) : NewsRemoteDataSource {
    suspend override fun getSources(category: String): SourcesResponse{
        return services.getSources(category = category)
    }
}