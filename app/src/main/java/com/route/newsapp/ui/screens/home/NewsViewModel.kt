package com.route.newsapp.ui.screens.home

import android.R.attr.category
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.route.newsapp.api.ApiManager
import com.route.newsapp.api.model.ArticleDM
import com.route.newsapp.api.model.ArticlesResponse
import com.route.newsapp.api.model.SourceDM
import com.route.newsapp.api.model.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class NewsViewModel : ViewModel() {//class to remove the logic from view
    var tabs : MutableLiveData<List<SourceDM>?> = MutableLiveData(null)
    var isLoading : MutableLiveData<Boolean> = MutableLiveData(false)
    var errorMessage : MutableLiveData<String?> = MutableLiveData(null)
    var articles : MutableLiveData<List<ArticleDM>?> = MutableLiveData(null)


    fun getSources(category: String)
    {
        isLoading.value = true
        ApiManager.getWebServices().getSources(category = category)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse?>,
                    response: Response<SourcesResponse?>
                ) {
                    isLoading.value = false
                    if (response.isSuccessful) {
                        tabs.value = response.body()!!.sources
                    } else {
                        errorMessage.value = response.message()
                    }
                }

                override fun onFailure(
                    call: Call<SourcesResponse?>,
                    t: Throwable
                ) {
                    isLoading.value = false
                    println("Failure: ${t.message}")
                    errorMessage.value = t.message ?: "Unknown error occurred"
                }
            })
    }

    fun getArticles(source: String) {
        ApiManager.getWebServices().getArticles(source = source)
            .enqueue(object : Callback<ArticlesResponse> {
                override fun onResponse(
                    call: Call<ArticlesResponse?>,
                    response: Response<ArticlesResponse?>
                ) {
                    isLoading.value = false
                    if (response.isSuccessful) {
                        articles.value = response.body()!!.articles
                    } else {
                        errorMessage.value = response.message()
                    }
                }

                override fun onFailure(
                    call: Call<ArticlesResponse?>,
                    t: Throwable
                ) {
                    isLoading.value = false
                    println("Failure: ${t.message}")
                    errorMessage.value = t.message ?: "Unknown error occurred"
                }

            })
    }

    fun searchArticles(source: String, query: String) {
        isLoading.value = true
        errorMessage.value = null

        ApiManager.getWebServices()
            .searchArticles(source = source, query = query)
            .enqueue(object : Callback<ArticlesResponse> {

                override fun onResponse(
                    call: Call<ArticlesResponse>,
                    response: Response<ArticlesResponse>
                ) {
                    isLoading.value = false

                    if (response.isSuccessful && response.body() != null) {
                        articles.value = response.body()!!.articles
                    } else {
                        errorMessage.value = response.message()
                    }
                }

                override fun onFailure(
                    call: Call<ArticlesResponse>,
                    t: Throwable
                ) {
                    isLoading.value = false
                    errorMessage.value = t.message ?: "Unknown error occurred"
                }
            })
    }
}