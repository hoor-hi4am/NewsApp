package com.route.newsapp.ui.screens.home.composables.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.route.newsapp.ui.model.Category
import com.route.newsapp.ui.model.categories
import com.route.newsapp.ui.theme.Black
import com.route.newsapp.ui.theme.Grey2
import com.route.newsapp.ui.theme.NewsDarkTypography
import com.route.newsapp.ui.theme.White

@Composable
fun CategoriesTab(onCategoryClick:(Category)->Unit){
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        item {
            Text("Good Morning\n" +
                    "Here is Some News For You", style = NewsDarkTypography.titleSmall
            )
        }
        itemsIndexed(categories){index,category->
            CategoryItem(category,index){
                onCategoryClick(category)
            }
        }
    }
}
@Composable
fun CategoryItem(category: Category, index: Int, onClick:()->Unit){
    var isEven = index % 2 == 0
    var iconModifier = Modifier
        .size(44.dp)
        .clip(CircleShape)
        .background(Black)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(188.dp)
            .padding(vertical = 8.dp)
            .clickable {
                onClick()
            }
    ){
        Image(
            painter = painterResource(id = category.image),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.FillBounds,
            contentDescription = category.title
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = if (isEven) Alignment.End else Alignment.Start
        ){
            Text(category.title, style = NewsDarkTypography.titleMedium)
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Grey2,
                    contentColor = White,
                    disabledContainerColor = White,
                    disabledContentColor = White
                )
            ){
                if(isEven){
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text("View All", style = NewsDarkTypography.bodyLarge,modifier = Modifier.padding(12.dp))
                        Spacer(modifier = Modifier.size(10.dp))
                        Icon(
                            Icons.AutoMirrored.Default.KeyboardArrowRight,
                            contentDescription = "",
                            tint = Color.White,
                            modifier = iconModifier
                        )
                    }
                }
                else{
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            Icons.AutoMirrored.Default.KeyboardArrowLeft,
                            contentDescription = "",
                            tint = Color.White,
                            modifier = iconModifier
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        Text("View All", style = NewsDarkTypography.bodyLarge,modifier = Modifier.padding(12.dp))
                    }
                }
            }

        }
    }

}