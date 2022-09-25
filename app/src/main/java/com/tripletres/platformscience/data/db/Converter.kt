package com.tripletres.platformscience.data.db

import com.tripletres.platformscience.data.db.shipment.ShipmentEntity

/**
 * Expected list of shipment and convert to shipment entity
 */
fun List<String>.asShipmentEntityList(): List<ShipmentEntity> {
    return this.map { address -> address.asShipmentEntity()}
}

/**
 * Converts an address string to shipment entity
 */
fun String.asShipmentEntity(): ShipmentEntity = ShipmentEntity(address = this)