package com.tripletres.platformscience.data.repo

import com.tripletres.platformscience.data.network.ShipmentService

/**
 * Shipments Repository
 */
class ShipmentRepository {
    //TODO: Add Dependency injection
    private val api: ShipmentService = ShipmentService()

    suspend fun fetchShipments(): List<String> {
        val shipments = api.getShipments()
        //TODO: Store shipments
        return shipments
    }

}