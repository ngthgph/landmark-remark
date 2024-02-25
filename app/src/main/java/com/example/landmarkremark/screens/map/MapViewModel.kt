package com.example.landmarkremark.screens.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MapViewModel: ViewModel() {
    companion object {
        val Factory = viewModelFactory {
            initializer {
                MapViewModel()
            }
        }
    }

    private val _uiState = MutableStateFlow(MapUiState())
    val uiState: StateFlow<MapUiState> = _uiState

    init {
        initializeUiState()
    }

    private fun initializeUiState() {
        _uiState.value = MapUiState()
    }

    fun updateSelectedLocation(location: LatLng) {
        _uiState.update { current ->
            current.copy(selectedLocation = location)
        }
    }
}