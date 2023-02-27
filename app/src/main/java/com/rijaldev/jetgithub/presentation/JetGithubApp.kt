package com.rijaldev.jetgithub.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rijaldev.jetgithub.presentation.navigation.Screen
import com.rijaldev.jetgithub.presentation.screen.home.HomeScreen
import com.rijaldev.jetgithub.presentation.screen.about.AboutScreen
import com.rijaldev.jetgithub.presentation.screen.detail.DetailScreen
import com.rijaldev.jetgithub.presentation.screen.splash.SplashScreen
import com.rijaldev.jetgithub.presentation.ui.theme.JetGithubTheme

@Composable
fun JetGithubApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = when (currentRoute) {
                Screen.Splash.route -> Color.White
                Screen.Home.route -> Color(0xFFA6B7FF)
                Screen.About.route -> Color.Black
                else -> Color.White
            },
            darkIcons = when (currentRoute) {
                Screen.DetailUser.route -> true
                else -> false
            }
        )
    }
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = modifier
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                navigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                navigateToAbout = {
                    navController.navigate(Screen.About.route)
                },
                onItemClicked = { userId ->
                    navController.navigate(Screen.DetailUser.createRoute(userId))
                }
            )
        }
        composable(Screen.About.route) {
            AboutScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable(
            route = Screen.DetailUser.route,
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType }
            )
        ) {
            val userId = it.arguments?.getInt("userId") ?: 0
            DetailScreen(
                id = userId,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4_XL)
@Composable
fun JetGithubAppPreview() {
    JetGithubTheme {
        JetGithubApp()
    }
}