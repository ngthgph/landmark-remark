package com.example.landmarkremark.ui

import com.google.android.gms.maps.model.LatLng

data class LandmarkUiState(
    var selectedLocation: LatLng? = null,
)
