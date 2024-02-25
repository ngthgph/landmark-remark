package com.example.landmarkremark.model.service.impl

import com.example.landmarkremark.model.Remark
import com.example.landmarkremark.model.service.AccountService
import com.example.landmarkremark.model.service.StorageService
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import com.google.firebase.perf.trace
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: AccountService
) : StorageService {
    private val collection get() = firestore.collection(REMARK_COLLECTION)
        .whereEqualTo(USER_ID,auth.currentUserId)

    @OptIn(ExperimentalCoroutinesApi::class)
    override val remarks: Flow<List<Remark>>
        get() =
            auth.currentUser.flatMapLatest { user ->
                firestore
                    .collection(REMARK_COLLECTION)
                    .whereEqualTo(USER_ID, user.id)
                    .orderBy(CREATED_AT_FIELD, Query.Direction.DESCENDING)
                    .dataObjects()
            }

    override suspend fun getRemark(remarkId: String): Remark? =
        firestore.collection(REMARK_COLLECTION).document(remarkId).get().await().toObject()

    override suspend fun getLandmarkRemark(remarkLatLng: LatLng): Remark? {
       val result = collection.whereEqualTo(LOCATION, remarkLatLng).get().await()
       return if (result.isEmpty) null else result.documents[0].toObject()
    }

    override suspend fun save(remark: Remark): String =
        trace(SAVE_REMARK_TRACE) {
            val updatedRemark = remark.copy(userId = auth.currentUserId)
            firestore.collection(REMARK_COLLECTION).add(updatedRemark).await().id
        }

    override suspend fun update(remark: Remark): Unit =
        trace(UPDATE_REMARK_TRACE) {
            firestore.collection(REMARK_COLLECTION).document(remark.id).set(remark).await()
        }

    override suspend fun delete(remarkId: String) {
        firestore.collection(REMARK_COLLECTION).document(remarkId).delete().await()
    }

    companion object {
        private const val USER_ID = "userId"
        private const val REMARK_COLLECTION = "remark"
        private const val CREATED_AT_FIELD = "createdAt"
        private const val LOCATION = "latLng"
        private const val SAVE_REMARK_TRACE = "saveRemark"
        private const val UPDATE_REMARK_TRACE = "updateRemark"
    }
}