package com.tripletres.platformscience.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tripletres.platformscience.ui.main.MainViewScreen

/**
 * Application navigation controller function
 */
@Composable
fun AppNav() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Router.MainView.route) {
        composable(Router.MainView.route) {
            MainViewScreen(navController = navController)
        }
    }

}