package com.tripletres.platformscience.domain.model

/**
 * Driver model simple for domain layer
 */
data class Driver(val id: Long, val name: String, val shipment: Shipment?, val ss: Float?)