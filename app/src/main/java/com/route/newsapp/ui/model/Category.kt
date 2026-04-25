package com.route.newsapp.ui.model

import com.route.newsapp.R

data class Category(
    val image: Int,
    val title: String
)

val categories = listOf(
    Category(image = R.drawable.general, title = "General"),
    Category(image = R.drawable.busniess, title = "Business"),
    Category(image = R.drawable.sport, title = "Sports"),
    Category(image = R.drawable.technology, title = "Technology"),
    Category(image = R.drawable.entertainment, title = "Entertainment"),
    Category(image = R.drawable.helth, title = "Health"),
    Category(image = R.drawable.science, title = "Science"),
)
