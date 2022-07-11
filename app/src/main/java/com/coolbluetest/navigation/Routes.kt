package com.coolbluetest.navigation

sealed class Routes(val route: String) {
    object Search: Routes("search")
}
