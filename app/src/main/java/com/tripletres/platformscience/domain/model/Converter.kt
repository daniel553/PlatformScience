package com.tripletres.platformscience.domain.model

import com.tripletres.platformscience.data.db.driver.DriverAssignationEntity
import com.tripletres.platformscience.data.db.driver.DriverEntity
import com.tripletres.platformscience.data.db.shipment.ShipmentEntity
import com.tripletres.platformscience.ui.model.DriverItem
import com.tripletres.platformscience.ui.model.ShipmentItem

/**
 * Parse a list of driver items from ui to a set of drivers
 */
@JvmName("asDriverListDriverItem")
fun List<DriverItem>.asDriverList(): List<Driver> = this.map { it.asDriver() }

@JvmName("asDriverListDriverEntity")
fun List<DriverEntity>.asDriverList(): List<Driver> = this.map { it.asDriver() }

@JvmName("asShipmentListShipmentItem")
fun List<ShipmentItem>.asShipmentList(): List<Shipment> = this.map { it.asShipment() }

@JvmName("asShipmentListShipmentEntity")
fun List<ShipmentEntity>.asShipmentList(): List<Shipment> = this.map { it.asShipment() }


/**
 * Parse driver item from ui to Domain driver
 */
fun DriverItem.asDriver(): Driver = Driver(id, name, null, 0f)

/**
 * Parse driver item from ui to Domain driver
 */
fun DriverEntity.asDriver(): Driver =
    Driver(id, name, assignation?.shipment?.asShipment(), assignation?.suitabilityScore)

fun ShipmentItem.asShipment(): Shipment = Shipment(id, address)

fun ShipmentEntity.asShipment(): Shipment = Shipment(id, address)


/**
 * Parse to driver entity for Database from Domain
 */
fun Driver.asDriverEntity(): DriverEntity {
    return DriverEntity(
        id = this.id,
        name = this.name,
        assignation = this.ss?.let {
            this.shipment?.asShipmentEntity()?.let { shipment ->
                DriverAssignationEntity(
                    shipment = shipment,
                    suitabilityScore = it
                )
            }
        }
    )
}

private fun Shipment.asShipmentEntity(): ShipmentEntity = ShipmentEntity(this.id, this.address)
