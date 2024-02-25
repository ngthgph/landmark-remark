package com.example.landmarkremark

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.landmarkremark.screens.login.LoginScreen
import com.example.landmarkremark.screens.map.MapScreen

const val LOGIN_SCREEN = "LoginScreen"
const val SIGN_UP_SCREEN = "SignUpScreen"
const val MAP_SCREEN = "MapScreen"

@Composable
fun LandmarkRemarkApp() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MAP_SCREEN
    ) {
        composable(MAP_SCREEN) {
            MapScreen(onAccount = { navController.navigate(LOGIN_SCREEN) })
        }
        composable(LOGIN_SCREEN) {
            LoginScreen(signUp = { navController.navigate(SIGN_UP_SCREEN) })
        }
    }
}

