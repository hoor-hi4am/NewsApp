package com.route.newsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.route.newsapp.ui.screens.home.HomeScreen
import com.route.newsapp.ui.screens.splash.SplashScreen
import com.route.newsapp.ui.utils.HomeRoute
import com.route.newsapp.ui.utils.SplashRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ){
        composable<HomeRoute>{
            HomeScreen(navController)
        }
        composable<SplashRoute>{
            SplashScreen(navController)
        }
    }

}

@Composable
@Preview
fun AppPreview(){
    App()
}
