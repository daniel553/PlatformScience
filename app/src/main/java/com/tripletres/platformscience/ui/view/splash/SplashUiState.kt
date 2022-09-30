package com.tripletres.platformscience.ui.view.splash

data class SplashUiState(
    val status: SplashUiStatus = SplashUiStatus.FETCHING
)

enum class SplashUiStatus {
    FETCHING,
    ASSIGNING,
    DONE,
}
