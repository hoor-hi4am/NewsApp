package com.route.newsc43.data.di

import com.route.newsapp.data.api.WebServices
import com.route.newsapp.utils.Connectivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun provideWebServices(retrofit: Retrofit): WebServices {
        return retrofit.create(WebServices::class.java)
    }
}