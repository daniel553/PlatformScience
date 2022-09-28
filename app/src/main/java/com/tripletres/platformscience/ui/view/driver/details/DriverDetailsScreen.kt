package com.tripletres.platformscience.ui.view.driver.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tripletres.platformscience.R
import com.tripletres.platformscience.ui.model.DriverItem
import com.tripletres.platformscience.ui.model.ShipmentItem

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
            sheetPeekHeight = 240.dp,
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
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "Up")
        Text(
            text = driver.name,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = driver.shipmentItem?.address ?: "",
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.padding(top = 4.dp)
        )
        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_truck), contentDescription = "truck"
            )
            Image(
                painter = painterResource(id = R.drawable.ic_man),
                contentDescription = "driver",
                alignment = Alignment.BottomEnd,
                modifier = Modifier
                    .padding(8.dp)
                    .clip(CircleShape)
                    .border(
                        width = 4.dp,
                        shape = CircleShape,
                        color = MaterialTheme.colors.primary
                    )
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.driver_ss),
                style = MaterialTheme.typography.button,
                modifier = Modifier.padding(end = 8.dp)
            )
            Badge(
                backgroundColor = MaterialTheme.colors.secondary
            ) {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "",
                    modifier = Modifier.width(16.dp)
                )
                Text(
                    text = driver.ss.toString(),
                    style = MaterialTheme.typography.button,
                )
            }
        }

        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(32.dp),
            modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
        ) {
            Text(
                text = stringResource(id = R.string.driver_start_route).uppercase(),
            )
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "",
                modifier = Modifier.padding(start = 4.dp)
            )
        }

    }

}

@Preview
@Composable
fun DriverDetailsBottomPreview() {
    DriverDetailsBottom(
        DriverItem(
            10,
            "Pedro Daniel García",
            ShipmentItem(10, "Evergreen 123 Avenue, Florida"),
            2f
        )
    )
}