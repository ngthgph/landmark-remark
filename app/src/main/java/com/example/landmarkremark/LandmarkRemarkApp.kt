package com.example.landmarkremark

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.landmarkremark.screens.account.AccountScreen
import com.example.landmarkremark.screens.account.AccountUiState
import com.example.landmarkremark.screens.account.AccountViewModel
import com.example.landmarkremark.screens.login.LoginScreen
import com.example.landmarkremark.screens.map.MapScreen
import com.example.landmarkremark.screens.signup.SignUpScreen

const val ACCOUNT_SCREEN = "AccountScreen"
const val LOGIN_SCREEN = "LoginScreen"
const val SIGN_UP_SCREEN = "SignUpScreen"
const val MAP_SCREEN = "MapScreen"

@Composable
fun LandmarkRemarkApp() {
    val viewModel: AccountViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState().value
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MAP_SCREEN
    ) {
        composable(MAP_SCREEN) {
            MapScreen(onAccount = {
                val screen = if(uiState.isAnonymousAccount) LOGIN_SCREEN else ACCOUNT_SCREEN
                navController.navigate(screen) })
        }
        composable(LOGIN_SCREEN) {
            LoginScreen(signUp = { navController.navigate(SIGN_UP_SCREEN) })
        }
        composable(SIGN_UP_SCREEN) {
            SignUpScreen(onReturn = { navController.navigate(MAP_SCREEN) })
        }
        composable(ACCOUNT_SCREEN) {
            AccountScreen(
                onReturn = { navController.navigate(MAP_SCREEN) },
            )
        }
    }
}

