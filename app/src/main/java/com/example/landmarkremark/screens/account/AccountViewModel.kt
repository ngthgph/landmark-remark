package com.example.landmarkremark.screens.account

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.landmarkremark.model.service.AccountService
import com.example.landmarkremark.model.service.LogService
import com.example.landmarkremark.screens.LandmarkRemarkViewModel
import com.google.firebase.auth.FirebaseAuthException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AccountViewModel@Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) : LandmarkRemarkViewModel(logService) {
    var uiState = accountService.currentUser.map { AccountUiState(it.isAnonymous, it.id) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = AccountUiState()
        )

    init {
        onAppStart()
    }
    fun onAppStart() {
        if (!accountService.hasUser) createAnonymousAccount()
    }
    private fun createAnonymousAccount() {
        launchCatching {
            try {
                accountService.createAnonymousAccount()
                uiState.value.isAnonymousAccount = true

            } catch (ex: FirebaseAuthException) {
                throw ex
            }
        }
    }
    fun onSignOutClick() {
        launchCatching {
            accountService.signOut()
        }
    }

    fun onDeleteMyAccountClick() {
        launchCatching {
            accountService.deleteAccount()
        }
    }
}