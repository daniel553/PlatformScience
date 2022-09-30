package com.tripletres.platformscience.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tripletres.platformscience.ui.view.driver.DriverListViewScreen
import com.tripletres.platformscience.ui.view.driver.details.DriverDetailsScreen
import com.tripletres.platformscience.ui.view.driver.details.DriverDetailsViewModel
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
        composable(
            route = Router.DriverDetailsScreen.route,
            arguments = listOf(navArgument(Router.ID) { type = NavType.LongType })
        ){ stack ->
            val viewModel = hiltViewModel<DriverDetailsViewModel>()
            val id = stack.arguments?.getLong(Router.ID)
            viewModel.setDriverId(id)
            DriverDetailsScreen(navController = navController, viewModel = viewModel)
        }
    }

}