package com.tripletres.platformscience.data.network

import com.tripletres.platformscience.util.RetrofitUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Shipment service IO operations
 */
class ShipmentService {
    //TODO: Inject dependency
    private val retrofit = RetrofitUtil.instance

    suspend fun getShipments(): List<String> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ShipmentApiClient::class.java).getAll()
            val body = response.body()
            body?.shipments ?: emptyList()
        }
    }
}