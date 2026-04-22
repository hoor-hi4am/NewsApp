package com.route.newsapp.ui.model

data class Category(
    val image: Int,
    val title: String
)
val categories = listOf<Category>(
    Category(image = com.route.newsapp.R.drawable.general, title = "General"),
    Category(image = com.route.newsapp.R.drawable.busniess, title = "Business"),
    Category(image = com.route.newsapp.R.drawable.sport, title = "Sports"),
    Category(image = com.route.newsapp.R.drawable.technology, title = "Technology"),
    Category(image = com.route.newsapp.R.drawable.entertainment, title = "Entertainment"),
    Category(image = com.route.newsapp.R.drawable.helth, title = "Health"),
    Category(image = com.route.newsapp.R.drawable.science, title = "Science"),
    )
