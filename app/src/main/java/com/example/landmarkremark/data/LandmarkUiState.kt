package com.example.landmarkremark.data

import com.google.android.gms.maps.model.LatLng

data class LandmarkUiState(
    var currentLocation: LatLng = LatLng(0.toDouble(), 0.toDouble())
)
