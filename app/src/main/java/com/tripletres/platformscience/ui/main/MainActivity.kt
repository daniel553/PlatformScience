package com.tripletres.platformscience.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tripletres.platformscience.ui.navigation.AppNav
import com.tripletres.platformscience.ui.theme.PlatformScienceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlatformScienceTheme {
               AppNav()
            }
        }
    }
}