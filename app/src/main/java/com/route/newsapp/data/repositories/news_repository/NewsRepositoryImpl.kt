package com.route.newsapp.data.repositories.news_repository

import android.Manifest
import androidx.annotation.RequiresPermission
import com.route.newsapp.data.api.model.SourceDM
import com.route.newsapp.data.mapper.SourcesMapper
import com.route.newsapp.data.repositories.news_repository.data_sources.news_local_data_source.NewsLocalDataSource
import com.route.newsapp.data.repositories.news_repository.data_sources.news_local_data_source.NewsLocalDataSourceImpl
import com.route.newsapp.data.repositories.news_repository.data_sources.news_remote_data_source.NewsRemoteDataSource
import com.route.newsapp.data.repositories.news_repository.data_sources.news_remote_data_source.NewsRemoteDataSourceImpl
import com.route.newsapp.domain.model.Source
import com.route.newsapp.domain.repository.NewsRepository
import com.route.newsapp.utils.Connectivity
import jakarta.inject.Inject

class NewsRepositoryImpl @Inject constructor(var localDataSource : NewsLocalDataSource,
                                             var remoteDataSource : NewsRemoteDataSource,
                                             var connectivity : Connectivity,
                                             var sourcesMapper: SourcesMapper
) : NewsRepository {

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    suspend override fun getSources(category: String): List<Source>{
        val isConnected = connectivity.isOnline()
        if (isConnected){
            val sourcesResponse = remoteDataSource.getSources(category)
            localDataSource.saveSources(category, sourcesResponse.sources!!)
            return sourcesMapper.toSources(sourcesResponse.sources)
        }else{
            return sourcesMapper.toSources(localDataSource.getSources(category))
        }
    }
}