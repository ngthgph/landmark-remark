package com.example.landmarkremark.data

import com.google.android.gms.maps.model.LatLng

data class LandmarkUiState(
    var selectedLocation: LatLng? = null,
)
