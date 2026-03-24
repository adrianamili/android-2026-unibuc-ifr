package com.unibucfmiifr2026.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.unibucfmiifr2026.ui.screens.HomeScreen
import com.unibucfmiifr2026.ui.screens.LoginScreen
import com.unibucfmiifr2026.ui.screens.RegisterScreen

@Composable
fun AuthNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                onRegisterClick = {
                    navController.navigate("register")
                },
                onLoginClick = {
                    navController.navigate("home")
                }
            )
        }
        composable("register") {
            RegisterScreen(
                onLoginClick = {
                    navController.popBackStack()
                },
                onRegisterClick = {
                    navController.navigate("home")
                }
            )

        }
        composable("home") {
            HomeScreen()
        }
    }
}