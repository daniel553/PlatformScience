package com.tripletres.platformscience.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tripletres.platformscience.ui.view.driver.DriverListViewScreen
import com.tripletres.platformscience.ui.view.splash.SplashScreenCompose

/**
 * Application navigation controller function
 */
@Composable
fun AppNav() {
    val navController = rememberNavController()

    //Navigation host for whole app
    NavHost(navController = navController, startDestination = Router.SplashScreen.route) {
        composable(Router.SplashScreen.route) {
            SplashScreenCompose(navController = navController, viewModel = hiltViewModel())
        }
        composable(Router.DriverListScreen.route) {
            DriverListViewScreen(navController = navController, viewModel = hiltViewModel())
        }
    }

}