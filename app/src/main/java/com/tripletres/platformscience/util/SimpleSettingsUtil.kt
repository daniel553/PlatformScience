package com.tripletres.platformscience.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Simple settings utility, allows to set shared preferences
 */
@Singleton
class SimpleSettingsUtil @Inject constructor(@ApplicationContext val context: Context) {

    companion object {
        private const val SETTINGS = "com.tripletres.platformscience."

        const val DB_OR_API = "DB_OR_API"
        const val DB_OR_API_DEF = "DB" //DB is default
        const val DB_OR_API_API = "API" //API option
        const val ALGORITHM: String = "ALGORITHM"
        const val ALGORITHM_ACO: String = "ACO"
        const val ALGORITHM_GREEDY: String = "GREEDY"
        const val ALGORITHM_BRANCH_BOUND: String = "BRANCH_BOUND"
        const val ALGORITHM_DEF: String = ALGORITHM_ACO


        /**
         * Gets all algorithms available
         * @return a list of settings name constants for algorithm
         */
        fun allAlgorithms(): List<String> {
            return listOf(
                ALGORITHM_ACO,
                ALGORITHM_GREEDY,
                ALGORITHM_BRANCH_BOUND
            )
        }

    }

    private val byDefault = mapOf(
        DB_OR_API to DB_OR_API_DEF,
        ALGORITHM to ALGORITHM_DEF
    )

    /**
     * Get a preference matching with key or default value in mode private
     */
    fun getPreference(setting: String): String {
        return with(context.getSharedPreferences(SETTINGS, MODE_PRIVATE)) {
            getString(setting, byDefault[setting])!!
        }
    }

    /**
     * Sets a preference in mode private
     */
    fun setPreference(setting: String, value: String) {
        with(context.getSharedPreferences(SETTINGS, MODE_PRIVATE).edit()) {
            putString(setting, value).commit()
        }
    }

}