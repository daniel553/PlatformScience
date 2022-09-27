package com.tripletres.platformscience.ui.view.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tripletres.platformscience.R
import com.tripletres.platformscience.ui.navigation.Router

/**
 * Custom splash screen for loading, google's refer to API usage instead:
 * https://developer.android.com/develop/ui/views/launch/splash-screen
 */
@Composable
fun SplashScreenCompose(navController: NavHostController, viewModel: SplashViewModel) {
    val uiState = viewModel.uiState.collectAsState()
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "",
                modifier = Modifier
                    .width(450.dp)
                    .padding(8.dp)
            )
            Text(
                text = stringResource(
                    id = when (uiState.value.status) {
                        SplashUiStatus.FETCHING -> R.string.splash_fetching
                        SplashUiStatus.ASSIGNING -> R.string.splash_assigning
                        SplashUiStatus.DONE -> R.string.splash_done
                    }
                ),
                modifier = Modifier.padding(8.dp)
            )
            CircularProgressIndicator(
                color = MaterialTheme.colors.surface
            )
        }
    }


    if(uiState.value.status == SplashUiStatus.DONE){
        LaunchedEffect(true){
            navController.navigate(Router.DriverListScreen.route){
                popUpTo(Router.SplashScreen.route)
            }
        }
    }

}