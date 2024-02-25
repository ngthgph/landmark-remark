package com.example.landmarkremark.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LandmarkViewModel: ViewModel() {
    companion object {
        val Factory = viewModelFactory {
            initializer {
                LandmarkViewModel()
            }
        }
    }

    private val _uiState = MutableStateFlow(LandmarkUiState())
    val uiState: StateFlow<LandmarkUiState> = _uiState

    init {
        initializeUiState()
    }

    private fun initializeUiState() {
        _uiState.value = LandmarkUiState()
    }

    fun updateSelectedLocation(location: LatLng) {
        _uiState.update { current ->
            current.copy(selectedLocation = location)
        }
    }
}