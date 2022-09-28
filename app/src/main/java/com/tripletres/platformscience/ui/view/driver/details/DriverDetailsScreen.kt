package com.tripletres.platformscience.ui.view.driver.details

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tripletres.platformscience.ui.navigation.Router
import com.tripletres.platformscience.ui.view.driver.DriverListView
import com.tripletres.platformscience.R
import com.tripletres.platformscience.ui.model.DriverItem

/**
 * Shows the driver item details
 */
@Composable
fun DriverDetailsScreen(navController: NavController, viewModel: DriverDetailsViewModel) {
    Scaffold(
        content = {
            DriverDetailsMainView(navController = navController, viewModel = viewModel)
        },
        modifier = Modifier.fillMaxSize()
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DriverDetailsMainView(navController: NavController, viewModel: DriverDetailsViewModel) {
    val driverUiState by viewModel.uiState.collectAsState()
    viewModel.getUserFromDB()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    )

    driverUiState.driver?.let { driver ->
        BottomSheetScaffold(
            scaffoldState = bottomSheetScaffoldState,
            sheetContent = { DriverDetailsBottom(driver) },
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            sheetPeekHeight = 160.dp,
            sheetElevation = 4.dp,
            sheetBackgroundColor = MaterialTheme.colors.primarySurface,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_route), contentDescription = "route",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(16.dp)
                    .width(52.dp)
                    .height(52.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = ""
                )
            }
        }
    }

}

@Composable
fun DriverDetailsBottom(driver: DriverItem) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "Up")
        Text(
            text = driver.name,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = driver.shipmentItem?.address ?: "",
            modifier = Modifier.padding(top = 4.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_truck), contentDescription = "truck"
            )
            Image(
                painter = painterResource(id = R.drawable.ic_man),
                contentDescription = "driver",
                modifier = Modifier.clip(CircleShape)
            )
        }

        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(32.dp)
        ) {
            Text(
                text = stringResource(id = R.string.driver_start_route).uppercase(),
            )
        }

    }

}