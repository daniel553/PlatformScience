package com.tripletres.platformscience.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.tripletres.platformscience.ui.main.splash.SplashScreenCompose
import com.tripletres.platformscience.ui.navigation.Router

@Composable
fun MainViewScreen(
    navController: NavController,
    viewModel: MainActivityViewModel
) {
    val mainUiState by viewModel.mainUiState.collectAsState()

    //Set main view when loading ends
    when(mainUiState.isLoading){
        true -> SplashScreenCompose()
        false -> navController.navigate(route = Router.DriverListScreen.route)
    }

    // A surface container using the 'background' color from the theme
    /*Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column() {
            Text(text = mainUiState.isLoading.toString())
        }
    }*/

}