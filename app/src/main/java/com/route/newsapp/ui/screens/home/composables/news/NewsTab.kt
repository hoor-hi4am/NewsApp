package com.route.newsapp.ui.screens.home.composables.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.newsapp.ui.composables.DefaultErrorMessage
import com.route.newsapp.ui.composables.DefaultLoadingView
import com.route.newsapp.ui.model.Category
import com.route.newsapp.ui.screens.home.NewsViewModel
import com.route.newsapp.ui.theme.Black
import com.route.newsapp.ui.theme.NewsDarkTypography
import com.route.newsapp.ui.theme.White

@Composable
fun NewsTab(category: Category) {




    var selectedIndex by remember { mutableIntStateOf(0) }
    val viewModel = viewModel<NewsViewModel>() //السهم الي خارج من الفيو و رايح للفيو موديل
    val tabs = viewModel.tabs.observeAsState()
    val isLoading = viewModel.isLoading.observeAsState()
    val errorMessage = viewModel.errorMessage.observeAsState()

    DisposableEffect(Unit) {
        viewModel.isLoading.value = true
        viewModel.getSources(category.title)
        onDispose {
            // Cleanup code if needed
        }
    }


    Column (){
        if(isLoading.value!!){
            DefaultLoadingView()
    }
        if(!tabs.value.isNullOrEmpty()){
            ScrollableTabRow(
                selectedTabIndex = selectedIndex,
                containerColor = Black,
                indicator = {tabPositions->
                    Box(
                        Modifier
                            .tabIndicatorOffset(tabPositions[selectedIndex])
                            .height(1.dp)
                            .background(color = White)
                    )
                },
                divider = {}
            ) {

                for (i in 0 until (viewModel.tabs.value?.size ?: -1)) {
                    var isSelected = selectedIndex == i
                    Tab(
                        selected = selectedIndex == i,
                        onClick = {
                            selectedIndex = i
                        }, modifier = Modifier.padding(8.dp),
                        text = {
                            Text(
                                text = viewModel.tabs.value!![i].name ?: "",
                                style = if(isSelected) NewsDarkTypography.bodyMedium
                                else NewsDarkTypography.bodySmall)
                        }
                    )
                }
            }
            ArticlesList(source = viewModel.tabs.value!![selectedIndex].id ?: "")
        }
        if(errorMessage.value?.isNotEmpty() == true)
        {
            DefaultErrorMessage(message = errorMessage.value!!) {

            }
        }
    }
}