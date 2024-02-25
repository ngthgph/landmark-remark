package com.example.landmarkremark.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.landmarkremark.model.service.LogService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class LandmarkRemarkViewModel (private val logService: LogService) : ViewModel() {
    fun launchCatching(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                logService.logNonFatalCrash(throwable)
            },
            block = block
        )
}