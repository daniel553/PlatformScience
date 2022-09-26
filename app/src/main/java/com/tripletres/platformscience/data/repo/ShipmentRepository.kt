package com.tripletres.platformscience.data.repo

import com.tripletres.platformscience.data.db.shipment.ShipmentDao
import com.tripletres.platformscience.data.db.shipment.ShipmentEntity
import javax.inject.Inject

/**
 * Shipments Repository
 */
class ShipmentRepository @Inject constructor(
    private val shipmentDao: ShipmentDao
) {

    suspend fun saveShipments(shipments: List<ShipmentEntity>) = shipmentDao.insertAll(shipments)

}