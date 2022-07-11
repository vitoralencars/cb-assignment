package com.coolbluetest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.coolbluetest.ui.screen.search.SearchScreen

@Composable
fun SetUpNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Search.route
    ) {
        composable(Routes.Search.route) { SearchScreen() }
    }
}
