package com.example.landmarkremark.model.service

import com.example.landmarkremark.model.Remark
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

interface StorageService {
    val remarks: Flow<List<Remark>>
    suspend fun getRemark(remarkId: String): Remark?
    suspend fun getLandmarkRemark(remarkLatLng: LatLng): Remark?
    suspend fun save(remark: Remark): String
    suspend fun update(remark: Remark)
    suspend fun delete(remarkId: String)
}