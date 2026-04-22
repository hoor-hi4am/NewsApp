package com.route.newsapp.ui.composables

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun DefaultErrorMessage(message: String , onRetry: () -> Unit) {
    //اللي هيستخدم الكومبزبل ده هيبعتله ماسدج و هيبعتله انت هتعمل ايه لو دوست على زرار الريتري
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(text = message)
        ElevatedButton(onClick = {
            onRetry()
        }) {
            Text(text = "Retry")
        }
    }

    }