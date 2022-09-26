package com.tripletres.platformscience.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun MainViewScreen(navController: NavController) {
    val viewModel = hiltViewModel<MainActivityViewModel>()
    
    val mainUiState by viewModel.mainUiState.collectAsState()
    
    Column() {
        Text(text = mainUiState.isLoading.toString())
    }
    

}