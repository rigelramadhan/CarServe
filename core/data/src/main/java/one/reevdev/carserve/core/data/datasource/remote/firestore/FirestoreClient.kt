package one.reevdev.carserve.core.data.datasource.remote.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.datasource.model.AvailableService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirestoreClient @Inject constructor() {

    private val db: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    fun getAllAvailableService(): Flow<Result<List<AvailableService>>> = callbackFlow {
        trySend(Result.Loading())
        val docRef = db.collection("services").document("available")
        docRef.get().addOnSuccessListener { document ->
            if (document.exists()) {
                val service = document.get("list") as List<*>
                val availableServices = service.mapNotNull {
                    val map = it as? Map<*, *>
                    map?.let { data ->
                        AvailableService(
                            data["service"] as String,
                            (data["price"] as Number).toDouble()
                        )
                    }
                }
                trySend(Result.Success(availableServices))
            }
        }.addOnFailureListener {
            trySend(Result.Error(it))
        }
        awaitClose { channel.close() }
    }

//    fun addAvailableService(services: List<AvailableService>): Flow<Result<Boolean>> = callbackFlow {
//        try {
//            trySend(Result.Loading())
//            val docRef = db.collection("services").document("available  ")
//            docRef.set(mapOf("list" to services)).addOnSuccessListener {
//                trySend(Result.Success(true))
//            }.addOnFailureListener {
//                trySend(Result.Error(it))
//            }.addOnCompleteListener {
//                Log.d("", "")
//            }
//        } catch (e: Exception) {
//            trySend(Result.Error(e))
//            Log.d("", "")
//        } finally {
//            awaitClose { channel.close() }
//        }
//    }
}