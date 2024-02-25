package com.example.landmarkremark.screens.login

import androidx.compose.runtime.mutableStateOf
import com.example.landmarkremark.model.service.AccountService
import com.example.landmarkremark.model.service.LogService
import com.example.landmarkremark.screens.LandmarkRemarkViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) : LandmarkRemarkViewModel(logService) {
    var uiState = mutableStateOf(LoginUiState())
        private set

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onSignInClick() {
        launchCatching {
            accountService.authenticate(email, password)
        }
    }

    fun onForgotPasswordClick() {
        launchCatching {
            accountService.sendRecoveryEmail(email)
        }
    }
}