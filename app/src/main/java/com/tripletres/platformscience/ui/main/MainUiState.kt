package com.tripletres.platformscience.ui.main

import com.tripletres.platformscience.ui.model.DriverItem

data class MainUiState(
    val isLoading: Boolean = false,
    val drivers: List<DriverItem> = emptyList()
)
