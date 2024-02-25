package com.example.landmarkremark.screens.map

import com.example.landmarkremark.model.Remark
import com.google.android.gms.maps.model.LatLng

data class MapUiState(
    var selectedLocation: LatLng? = null,
    var temporaryRemark: Remark? = null
)
