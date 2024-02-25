package com.example.landmarkremark.model

import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Remark(
    @DocumentId var id: String = "",
    @ServerTimestamp val createdAt: Date = Date(),
    val title: String = "",
    val note: String = "",
    val latLng: LatLng? = null,
    val userId: String = ""
)
