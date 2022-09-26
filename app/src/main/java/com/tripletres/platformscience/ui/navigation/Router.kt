package com.tripletres.platformscience.ui.navigation

sealed class Router(val route: String) {
    object MainView: Router("MainView")
}
