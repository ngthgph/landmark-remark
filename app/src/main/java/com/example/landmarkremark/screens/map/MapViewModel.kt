package com.example.landmarkremark.screens.map

import com.example.landmarkremark.model.service.LogService
import com.example.landmarkremark.model.service.StorageService
import com.example.landmarkremark.screens.LandmarkRemarkViewModel
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MapViewModel@Inject constructor(
    logService: LogService,
    private val storageService: StorageService,
): LandmarkRemarkViewModel(logService) {

    val remarks = storageService.remarks

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