package com.route.newsapp.ui.screens.home.composables.news

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.route.newsapp.api.model.ArticleDM
import com.route.newsapp.ui.composables.DefaultErrorMessage
import com.route.newsapp.ui.composables.DefaultLoadingView
import com.route.newsapp.ui.screens.home.NewsViewModel
import com.route.newsapp.ui.theme.Black
import com.route.newsapp.ui.theme.NewsDarkTypography
import com.route.newsapp.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun ArticlesList(source: String) {

    val viewModel = viewModel<NewsViewModel>()
    val articles = viewModel.articles.observeAsState()
    val isLoading = viewModel.isLoading.observeAsState()
    val errorMessage = viewModel.errorMessage.observeAsState()

    var selectedArticle by remember { mutableStateOf<ArticleDM?>(null) }
    var query by remember { mutableStateOf("") }

    DisposableEffect(source) {
        viewModel.getArticles(source)
        onDispose {}
    }

    Column(){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .border(
                    width = 1.dp,
                    color = androidx.compose.ui.graphics.Color.White,
                    shape = RoundedCornerShape(16.dp)
                ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .background(Black)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = androidx.compose.ui.graphics.Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                androidx.compose.material3.TextField(
                    value = query,
                    onValueChange = {
                        query = it
                        if (query.isBlank()) {
                            viewModel.getArticles(source)
                        } else {
                            viewModel.searchArticles(source, query)
                        }
                    },
                    modifier = Modifier.weight(1f),
                    placeholder = {
                        Text("Search", color = androidx.compose.ui.graphics.Color.Gray)
                    },
                    colors = androidx.compose.material3.TextFieldDefaults.colors(
                        focusedContainerColor = Black,
                        unfocusedContainerColor = Black,
                        disabledContainerColor = Black,
                        focusedTextColor = androidx.compose.ui.graphics.Color.White,
                        unfocusedTextColor = androidx.compose.ui.graphics.Color.White,
                        cursorColor = androidx.compose.ui.graphics.Color.White
                    )
                )

                if (query.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = null,
                        tint = androidx.compose.ui.graphics.Color.White,
                        modifier = Modifier.clickable {
                            query = ""
                            viewModel.getArticles(source)
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {

            if (isLoading.value == true) {
                item { DefaultLoadingView() }
            }

            if (errorMessage.value?.isNotEmpty() == true) {
                item {
                    DefaultErrorMessage(message = errorMessage.value!!) {}
                }
            }

            if (!articles.value.isNullOrEmpty()) {
                items(articles.value!!) { article ->
                    ArticleItem(article = article) {
                        selectedArticle = article
                    }
                }
            }
        }

    }

    if (selectedArticle != null) {
        val context = LocalContext.current
        ModalBottomSheet(
            onDismissRequest = { selectedArticle = null }
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                GlideImage(
                    model = selectedArticle!!.urlToImage,
                    contentDescription = "Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = selectedArticle!!.description ?: "No description available",
                    style = NewsDarkTypography.bodySmall.copy(Black)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Button(
                    onClick = {
                        selectedArticle?.url?.let { url ->
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(
                        vertical = 16.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Black,
                        contentColor = androidx.compose.ui.graphics.Color.White
                    )
                ) {
                    Text(
                        "View Full Article",
                        style = NewsDarkTypography.bodyMedium.copy(White)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ArticleItem(
    article: ArticleDM,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(
                width = 1.dp,
                color = androidx.compose.ui.graphics.Color.White,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .background(Black)
                .padding(16.dp),
        ) {
            GlideImage(
                model = article.urlToImage,
                contentDescription = "Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(article.title ?: "", style = NewsDarkTypography.bodyMedium)
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(article.author ?: "", style = NewsDarkTypography.labelLarge)
                Text(article.publishedAt ?: "", style = NewsDarkTypography.labelLarge)
            }
        }
    }
}