package com.route.newsapp.data.di

import com.route.newsapp.data.repositories.news_repository.NewsRepositoryImpl
import com.route.newsapp.data.repositories.news_repository.data_sources.news_local_data_source.NewsLocalDataSource
import com.route.newsapp.data.repositories.news_repository.data_sources.news_local_data_source.NewsLocalDataSourceImpl
import com.route.newsapp.data.repositories.news_repository.data_sources.news_remote_data_source.NewsRemoteDataSource
import com.route.newsapp.data.repositories.news_repository.data_sources.news_remote_data_source.NewsRemoteDataSourceImpl
import com.route.newsapp.domain.repository.NewsRepository
import com.route.newsapp.utils.Connectivity
import com.route.newsapp.utils.ConnectivityImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
//الموديول ده هيربط الانترفيسز بالتشايلد بتاعتها
abstract class AppModule {

    @Binds
    //Binds -> بتربط الاتنين ببعض الي هو لو انا طلبت منك الانترفيس هتبعتلي الاامبليمينتيشن
    abstract fun bindNewsRepo(arg: NewsRepositoryImpl): NewsRepository

    @Binds
    abstract fun bindNewsLocal(arg: NewsLocalDataSourceImpl): NewsLocalDataSource

    @Binds
    abstract fun bindNewsRemote(arg: NewsRemoteDataSourceImpl): NewsRemoteDataSource

    @Binds
    abstract fun bindNewsConnectivity(arg: ConnectivityImpl): Connectivity
}