package com.tripletres.platformscience.data.repo

import com.tripletres.platformscience.data.network.ShipmentService
import javax.inject.Inject

/**
 * Shipments Repository
 */
class ShipmentRepository @Inject constructor(private val api: ShipmentService) {

    suspend fun fetchShipments(): List<String> {
        val shipments = api.getShipments()
        //TODO: Store shipments
        return shipments
    }

}