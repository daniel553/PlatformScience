package com.tripletres.platformscience.ui.model

/**
 * Driver Item model for UI
 */
data class DriverItem(
    val id: Long,
    val name: String,
    val shipmentItem: ShipmentItem?,
    val ss: Float?,
    val profilePic: Int? = 0
)
