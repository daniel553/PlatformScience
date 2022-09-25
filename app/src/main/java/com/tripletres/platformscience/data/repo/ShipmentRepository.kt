package com.tripletres.platformscience.data.repo

import com.tripletres.platformscience.data.db.asShipmentEntityList
import com.tripletres.platformscience.data.db.shipment.ShipmentDao
import com.tripletres.platformscience.data.db.shipment.ShipmentEntity
import com.tripletres.platformscience.data.network.ShipmentService
import javax.inject.Inject

/**
 * Shipments Repository
 */
class ShipmentRepository @Inject constructor(
    private val api: ShipmentService,
    private val shipmentDao: ShipmentDao
) {
    suspend fun fetchShipments(): List<String> {
        val shipments = api.getShipments()
        if (shipments.isNotEmpty()) {
            saveShipments(shipments.asShipmentEntityList())
        }
        return shipments
    }

    suspend fun saveShipments(shipments: List<ShipmentEntity>) = shipmentDao.insertAll(shipments)

}