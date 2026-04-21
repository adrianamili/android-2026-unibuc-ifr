package com.unibucfmiifr2026.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.unibucfmiifr2026.ui.screens.AddressDetailScreen
import com.unibucfmiifr2026.ui.screens.HomeScreen
import com.unibucfmiifr2026.ui.screens.LoginScreen
import com.unibucfmiifr2026.ui.screens.RegisterScreen
import com.unibucfmiifr2026.viewmodels.AuthViewModel

@Composable
fun AuthNavigation(authViewModel: AuthViewModel = viewModel()) {
    val navController = rememberNavController()
    val authState by authViewModel.authState.collectAsState()
    val navigateToHome = { navController.navigate("home") }
    val startDestination = if (authViewModel.isLoggedIn) "home" else "login"

    NavHost(
        navController,
        startDestination = startDestination
    ) {
        composable("login") {
            LoginScreen(
                onRegisterClick = {
                    navController.navigate("register")
                },
                onLoginClick = { email, password ->
                    authViewModel.login(email, password, onSuccess = navigateToHome)
//                    navController.navigate("home")
                },
                isLoading = authState.isLoading,
                authError = authState.error
            )
        }
        composable("register") {
            RegisterScreen(
                onLoginClick = {
                    navController.popBackStack()
                },
                onRegisterClick = { email, password ->
                    authViewModel.register(email, password, onSuccess = navigateToHome)
//                    navController.navigate("home")
                },
                isLoading = authState.isLoading,
                authError = authState.error
            )

        }
        composable("home") {
            HomeScreen(logout = authViewModel::logout)
            { addressId ->
                navController.navigate("addresses/${addressId}")
            }
        }
        composable(
            "addresses/{addressId}",
            listOf(navArgument("addressId") {
                type = NavType.LongType
            })
        ) {
            val addressId = it.arguments?.getLong("addressId") ?: return@composable
            AddressDetailScreen(addressId = addressId)
        }
    }
}