package com.example.landmarkremark.screens.signup

import androidx.compose.runtime.mutableStateOf
import com.example.landmarkremark.model.service.AccountService
import com.example.landmarkremark.model.service.LogService
import com.example.landmarkremark.screens.LandmarkRemarkViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) : LandmarkRemarkViewModel(logService) {
    var uiState = mutableStateOf(SignUpUiState())
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

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }

    fun onSignUpClick(){
        if (password != uiState.value.repeatPassword) {
            return
        }
        launchCatching {
            accountService.linkAccount(email, password)
        }
    }
}