package com.tripletres.platformscience.data.db.driver

import androidx.room.Embedded
import androidx.room.Entity
import com.tripletres.platformscience.data.db.shipment.ShipmentEntity

@Entity
data class DriverAssignationEntity(
    @Embedded val shipment: ShipmentEntity,
    val suitabilityScore: Float
)
