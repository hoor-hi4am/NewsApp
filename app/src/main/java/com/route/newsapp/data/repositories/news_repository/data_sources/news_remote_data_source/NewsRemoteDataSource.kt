package com.route.newsapp.data.repositories.news_repository.data_sources.news_remote_data_source

import com.route.newsapp.data.api.ApiManager
import com.route.newsapp.data.api.model.SourcesResponse

interface NewsRemoteDataSource {
    suspend fun getSources(category: String): SourcesResponse
}