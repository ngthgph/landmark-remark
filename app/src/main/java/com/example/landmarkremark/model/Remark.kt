package com.example.landmarkremark.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Remark(
    @DocumentId var id: String = "",
    @ServerTimestamp val createdAt: Date = Date(),
    val note: String = "",
    val latitude: Double,
    val longitude: Double,
    val userId: String = ""
)
