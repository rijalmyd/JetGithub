package com.rijaldev.jetgithub.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_page")
    object Home : Screen("home_page")
    object About : Screen("about_page")
    object DetailUser : Screen("detail_user_page/{userId}") {
        fun createRoute(userId: Int) = "detail_user_page/$userId"
    }
}