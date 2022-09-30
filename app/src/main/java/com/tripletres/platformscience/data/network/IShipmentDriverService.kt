package com.tripletres.platformscience.data.network

import com.tripletres.platformscience.data.model.ShipmentDriverResponse

/**
 * Shipments with driver api service contract class
 */
interface IShipmentDriverService {

    /**
     * Gets a list of shipments and Drivers
     */
    suspend fun getShipmentsWithDrivers(): ShipmentDriverResponse
}