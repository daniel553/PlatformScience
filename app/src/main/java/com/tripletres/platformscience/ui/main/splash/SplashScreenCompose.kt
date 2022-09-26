package com.tripletres.platformscience.ui.main.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tripletres.platformscience.R

/**
 * Custom splash screen for loading, google's refer to API usage instead:
 * https://developer.android.com/develop/ui/views/launch/splash-screen
 */
@Composable
fun SplashScreenCompose() {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement =  Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "",
                modifier = Modifier
                    .width(450.dp)
                    .padding(8.dp)
            )
            CircularProgressIndicator(
                color = MaterialTheme.colors.surface
            )
        }
    }
}

@Preview
@Composable
fun SplashScreenComposePreview() {
    SplashScreenCompose()
}