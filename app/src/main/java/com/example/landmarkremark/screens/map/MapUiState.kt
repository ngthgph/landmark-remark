package com.example.landmarkremark.screens.map

import com.google.android.gms.maps.model.LatLng

data class MapUiState(
    var selectedLocation: LatLng? = null,
)
