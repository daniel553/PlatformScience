package com.tripletres.platformscience.data.network

import com.tripletres.platformscience.data.model.ShipmentDriverResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Shipment api client class to create interface functions based on API REST server
 */
interface ShipmentApiClient {

    @GET("data.json")
    suspend fun getAll(): Response<ShipmentDriverResponse>
}