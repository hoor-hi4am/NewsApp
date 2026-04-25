package com.route.newsapp.domain.repository

import com.route.newsapp.data.api.model.SourceDM
import com.route.newsapp.domain.model.Source

interface NewsRepository {
    suspend fun getSources(category: String): List<Source>
}
