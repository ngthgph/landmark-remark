package com.example.landmarkremark.ui

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.landmarkremark.data.LandmarkUiState
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(
    viewModel: LandmarkViewModel,
    uiState: LandmarkUiState,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Initialize permissions
    val permissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    // Check that the button of permissions request should be shown or not
    // - case 1. false if permissions are allow => button does not show
    // - case 2. true if permissions are not grant => button shows
    var isButtonShow by remember { mutableStateOf(true) }

    // Configure Permission Launcher
    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        permissionsMap ->
        if (permissionsMap.values.reduce { acc, next -> acc && next }) {
            // - case 1. false if permissions are allow => button does not show
            isButtonShow = false
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }
    Box {
        // Check if necessary permissions are granted,
        // if yes get the current location
        if (permissions.all {
                ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
            }) {
            // - case 1. false if permissions are allow => button does not show
            isButtonShow = false
            // Get the current location
            viewModel.startLocationUpdate(context)

            // Set the camera position to the current location
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(uiState.currentLocation, 10f)
            }
            val uiSettings = remember {
                MapUiSettings(myLocationButtonEnabled = true)
            }
            val properties by remember {
                mutableStateOf(MapProperties(isMyLocationEnabled = true))
            }
            GoogleMap(
                modifier = modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = properties,
                uiSettings = uiSettings
            )

        // If necessary permissions are not granted,
        // Show button of launching the Toast to ask user to grant permissions
        } else {
            // Check if button is shown
            if(isButtonShow){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(onClick = {
                        launcherMultiplePermissions.launch(permissions)
                    }){
                        Text(text = "Access your location")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MapScreenPreview() {
}