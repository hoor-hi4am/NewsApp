package com.route.newsapp.ui.screens.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.newsapp.data.api.ApiManager
import com.route.newsapp.data.api.model.ArticleDM
import com.route.newsapp.data.api.model.SourceDM
import com.route.newsapp.data.repositories.news_repository.NewsRepository
import kotlinx.coroutines.launch


class NewsViewModel : ViewModel() {//class to remove the logic from view
    var newsRepository = NewsRepository()
    var tabs : MutableLiveData<List<SourceDM>?> = MutableLiveData(null)
    var isLoading : MutableLiveData<Boolean> = MutableLiveData(false)
    var errorMessage : MutableLiveData<String?> = MutableLiveData(null)
    var articles : MutableLiveData<List<ArticleDM>?> = MutableLiveData(null)


    fun getSources(category: String)
    {
        isLoading.value = true
        viewModelScope.launch {
            try {
                tabs.value =newsRepository.getSources(category = category)
                isLoading.value = false
            }catch (t: Throwable){
                isLoading.value = false
                println("Failure: ${t.message}")
                errorMessage.value = t.message ?: "Unknown error occurred"
            }

        }
//        ApiManager.getWebServices().getSources(category = category)
//            .enqueue(object : Callback<SourcesResponse> {
//                override fun onResponse(
//                    call: Call<SourcesResponse?>,
//                    response: Response<SourcesResponse?>
//                ) {
//                    isLoading.value = false
//                    if (response.isSuccessful) {
//                        tabs.value = response.body()!!.sources
//                    } else {
//                        errorMessage.value = response.message()
//                    }
//                }
//
//                override fun onFailure(
//                    call: Call<SourcesResponse?>,
//                    t: Throwable
//                ) {
//                    isLoading.value = false
//                    println("Failure: ${t.message}")
//                    errorMessage.value = t.message ?: "Unknown error occurred"
//                }
//            })
    }

    fun getArticles(source: String) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                isLoading.value = false
                val articlesResponse = ApiManager.getWebServices().getArticles(source = source)
                articles.value = articlesResponse.articles
            } catch (t: Throwable) {
                isLoading.value = false
                println("Failure: ${t.message}")
                errorMessage.value = t.message ?: "Unknown error occurred"

            }
        }
    }

    fun searchArticles(source: String, query: String) {
        viewModelScope.launch {
            isLoading.value = true
            errorMessage.value = null
            try {
                val response = ApiManager.getWebServices()
                    .searchArticles(source = source, query = query)
                articles.value = response.articles
                isLoading.value = false
            } catch (t: Throwable) {
                isLoading.value = false
                errorMessage.value = t.message ?: "Unknown error occurred"
            }
        }
    }
}