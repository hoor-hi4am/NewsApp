package com.route.newsapp.domain.usecases

import com.route.newsapp.domain.repository.NewsRepository
import jakarta.inject.Inject

class GetSourcesUseCase @Inject constructor(var newsRepository: NewsRepository) {
    suspend fun execute(category: String) = newsRepository.getSources(category)
}