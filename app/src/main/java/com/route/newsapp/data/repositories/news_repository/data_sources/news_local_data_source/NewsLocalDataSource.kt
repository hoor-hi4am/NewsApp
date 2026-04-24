package com.route.newsapp.data.repositories.news_repository.data_sources.news_local_data_source

import androidx.compose.ui.text.toLowerCase
import com.route.newsapp.data.api.model.SourceDM
import com.route.newsapp.data.api.model.SourcesResponse
import com.route.newsapp.data.database.MyDatabase
import java.util.Locale
import java.util.Locale.getDefault

class NewsLocalDataSource {
    var dao = MyDatabase.getDatabase().getSourcesDao()
    suspend fun getSources(category: String): List<SourceDM>{
        return dao.getSources(category.lowercase(getDefault()))
    }
    suspend fun saveSources(category: String, sources: List<SourceDM>){
        dao.saveSources(sources)

    }
}