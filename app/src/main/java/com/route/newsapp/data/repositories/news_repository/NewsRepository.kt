package com.route.newsapp.data.repositories.news_repository

import android.Manifest
import androidx.annotation.RequiresPermission
import com.route.newsapp.data.api.model.SourceDM
import com.route.newsapp.data.api.model.SourcesResponse
import com.route.newsapp.data.repositories.news_repository.data_sources.news_local_data_source.NewsLocalDataSource
import com.route.newsapp.data.repositories.news_repository.data_sources.news_remote_data_source.NewsRemoteDataSource
import com.route.newsapp.utils.Connectivity

class NewsRepository {
    var localDataSource = NewsLocalDataSource()
    var remoteDataSource = NewsRemoteDataSource()
    var connectivity = Connectivity()
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    suspend fun getSources(category: String): List<SourceDM>{
        val isConnected = connectivity.isOnline()
        if (isConnected){
            val sourcesResponse = remoteDataSource.getSources(category)
            localDataSource.saveSources(category, sourcesResponse.sources!!)
            return sourcesResponse.sources
        }else{
            return localDataSource.getSources(category)
        }
    }
}