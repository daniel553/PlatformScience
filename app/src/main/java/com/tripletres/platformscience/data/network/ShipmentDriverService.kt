package com.tripletres.platformscience.data.network

import com.tripletres.platformscience.data.model.ShipmentDriverResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Shipment with Drivers service IO operations class
 */
class ShipmentDriverService @Inject constructor(
    private val shipmentDriverApiClient: ShipmentDriverApiClient
) : IShipmentDriverService {

    override suspend fun getShipmentsWithDrivers(): ShipmentDriverResponse {
        return withContext(Dispatchers.IO) {
            val response = shipmentDriverApiClient.getAll()
            response.body() ?: ShipmentDriverResponse(emptyList(), emptyList())
        }
    }

}