package com.tripletres.platformscience.data.db

import com.tripletres.platformscience.data.db.driver.DriverEntity
import com.tripletres.platformscience.data.db.shipment.ShipmentEntity

/**
 * Expected list of shipment and convert to shipment entity
 */
fun List<String>.asShipmentEntityList(): List<ShipmentEntity> {
    return this.map { address -> address.asShipmentEntity()}
}

/**
 * Expected list of drivers names to convert driver entity list
 */
fun List<String>.asDriverEntityList(): List<DriverEntity> {
    return this.map { name -> name.asDriverEntity()}
}

/**
 * Converts an address string to shipment entity
 */
fun String.asShipmentEntity(): ShipmentEntity = ShipmentEntity(address = this)

fun String.asDriverEntity(): DriverEntity = DriverEntity(name = this, assignation = null)
