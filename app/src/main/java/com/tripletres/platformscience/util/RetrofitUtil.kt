package com.tripletres.platformscience.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Simple retrofit configuration
 */
object RetrofitUtil {
    private const val BASE_URL = "https://www.tripletres.com/platformscience/"

    fun instance(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}