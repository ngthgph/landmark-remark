package com.example.landmarkremark.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.landmarkremark.data.LandmarkUiState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
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

    fun updateCurrentLocation(location: LatLng) {
        _uiState.update { current ->
            current.copy(currentLocation = location)
        }
    }

    @Suppress("DEPRECATION")
    @SuppressLint("MissingPermission")
    fun startLocationUpdate(context: Context) {
        val locationCallback: LocationCallback?
        val fusedLocationClient: FusedLocationProviderClient?

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                for (lo in p0.locations) {
                    // Update UI with location data
                    updateCurrentLocation(LatLng(lo.latitude, lo.longitude))
                }
            }
        }
        locationCallback.let {
            val locationRequest = LocationRequest.create().apply {
                interval = 10000
                fastestInterval = 5000
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            }
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                it,
                Looper.getMainLooper()
            )
        }
    }
}