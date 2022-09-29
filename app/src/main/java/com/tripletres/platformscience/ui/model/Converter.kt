package com.tripletres.platformscience.ui.model

import com.tripletres.platformscience.domain.model.Driver
import com.tripletres.platformscience.domain.model.Shipment


/**
 * Parse driver list as ui driver item
 */
fun List<Driver>.asDriverItemList(): List<DriverItem> = this.map { it.asDriverItem() }

fun Driver.asDriverItem(): DriverItem {
    return DriverItem(this.id, this.name, this.shipment?.asShipmentItem(), this.ss)
}


/**
 * Parse as shipment item for ui
 */
fun Shipment?.asShipmentItem(): ShipmentItem? {
    return this?.let { ShipmentItem(it.id, it.address) }
}
