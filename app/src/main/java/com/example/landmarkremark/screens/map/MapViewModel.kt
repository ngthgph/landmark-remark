package com.example.landmarkremark.screens.map

import com.example.landmarkremark.model.Remark
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

    fun updateSelectedLocation(location: LatLng?) {
        _uiState.update { current ->
            current.copy(selectedLocation = location)
        }
    }
    fun updateTemporaryRemark(remark: Remark?) {
        _uiState.update { current ->
            current.copy(temporaryRemark = remark)
        }
    }
    fun updateTemporaryRemarkNote(note: String) {
        val remark = uiState.value.temporaryRemark?.copy(note = note)
        _uiState.update { current ->
            current.copy(temporaryRemark = remark)
        }
    }
    fun onSaveRemark(remark: Remark) {
        launchCatching {
            if (remark.id.isBlank()) {
                storageService.save(remark)
            } else {
                storageService.update(remark)
            }
            updateSelectedLocation(null)
            updateTemporaryRemark(null)
        }
    }
}