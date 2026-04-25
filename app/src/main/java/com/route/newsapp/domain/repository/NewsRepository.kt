package com.route.newsapp.domain.repository

import com.route.newsapp.data.api.model.SourceDM

interface NewsRepository {
    suspend fun getSources(category: String): List<SourceDM>
}
