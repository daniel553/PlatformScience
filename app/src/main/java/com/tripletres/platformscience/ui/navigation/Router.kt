package com.tripletres.platformscience.ui.navigation

/**
 * Router definition class
 * @param route to be evaluated
 */
sealed class Router(val route: String) {
    object MainViewScreen: Router("MainViewScreen")
    object DriverListScreen: Router("DriverListScreen")
}
