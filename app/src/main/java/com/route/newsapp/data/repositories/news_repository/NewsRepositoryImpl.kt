package com.route.newsapp.data.repositories.news_repository

import android.Manifest
import androidx.annotation.RequiresPermission
import com.route.newsapp.data.api.model.SourceDM
import com.route.newsapp.data.repositories.news_repository.data_sources.news_local_data_source.NewsLocalDataSource
import com.route.newsapp.data.repositories.news_repository.data_sources.news_local_data_source.NewsLocalDataSourceImpl
import com.route.newsapp.data.repositories.news_repository.data_sources.news_remote_data_source.NewsRemoteDataSource
import com.route.newsapp.data.repositories.news_repository.data_sources.news_remote_data_source.NewsRemoteDataSourceImpl
import com.route.newsapp.domain.repository.NewsRepository
import com.route.newsapp.utils.Connectivity
import jakarta.inject.Inject

class NewsRepositoryImpl @Inject constructor(var localDataSource : NewsLocalDataSource,
                                             var remoteDataSource : NewsRemoteDataSource,
                                             var connectivity : Connectivity
) : NewsRepository {

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    suspend override fun getSources(category: String): List<SourceDM>{
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