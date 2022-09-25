package com.tripletres.platformscience.data.network

import com.tripletres.platformscience.util.RetrofitUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Shipment service IO operations
 */
class ShipmentService @Inject constructor(private val shipmentApiClient: ShipmentApiClient) {

    suspend fun getShipments(): List<String> {
        return withContext(Dispatchers.IO) {
            val response = shipmentApiClient.getAll()
            val body = response.body()
            body?.shipments ?: emptyList()
        }
    }

}