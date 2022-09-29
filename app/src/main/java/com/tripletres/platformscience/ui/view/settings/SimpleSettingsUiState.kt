package com.tripletres.platformscience.ui.view.settings

data class SimpleSettingsUiState(
    val dbOrApi: Boolean, //DB = true
    val dbOrApiLabel: String = "",
    val algorithms: List<String> = emptyList(),
    val algorithmSelected: String = ""
)
