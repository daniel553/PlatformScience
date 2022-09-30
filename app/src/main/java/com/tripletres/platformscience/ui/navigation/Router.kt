package com.tripletres.platformscience.ui.navigation

/**
 * Router definition class
 * @param route to be evaluated
 */
sealed class Router(val route: String) {
    object SplashScreen : Router("LoadingScreen")
    object DriverListScreen : Router("DriverListScreen")
    object DriverDetailsScreen : Router("DriverDetailsScreen".plus("/{$ID}"))

    fun buildRoute(arg: String): String {
        return route.replace(Regex("\\{(.*)?\\}"), "").plus(arg)
    }

    companion object {
        const val ID = "id"
    }
}
