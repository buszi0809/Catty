package com.kwdev.catty.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kwdev.catty.ui.favorites.FavoritesScreen
import com.kwdev.catty.ui.navigation.NavRoute.Favorites
import com.kwdev.catty.ui.navigation.NavRoute.Home
import com.kwdev.catty.ui.home.HomeScreen

@Composable
fun CattyNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Home,
    ) {
        composable<Home> {
            HomeScreen(navController)
        }
        composable<Favorites> {
            FavoritesScreen(navController)
        }
    }
}
