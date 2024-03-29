package com.example.landmarkremark.screens.map

import android.Manifest
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.landmarkremark.LandmarkRemarkActivity
import com.example.landmarkremark.model.Remark
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.GoogleMapComposable
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun GoogleMapWindow(
    modifier: Modifier = Modifier,
    viewModel: MapViewModel = hiltViewModel(),
    uiState: MapUiState,
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

            // Get the current location of the device
            val fusedLocationProviderClient =
                remember { LocationServices.getFusedLocationProviderClient(context) }
            var lastKnownLocation by remember {
                mutableStateOf<Location?>(null)
            }
            var deviceLatLng by remember {
                mutableStateOf(LatLng(0.0, 0.0))
            }
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(deviceLatLng, 18f)
            }
            val locationResult = fusedLocationProviderClient.lastLocation
            locationResult.addOnCompleteListener(context as LandmarkRemarkActivity) { task ->
                if (task.isSuccessful) {
                    // Set the map's camera position to the current location of the device
                    lastKnownLocation = task.result
                    lastKnownLocation?.let { deviceLatLng = LatLng(lastKnownLocation!!.latitude, lastKnownLocation!!.longitude) }
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(deviceLatLng, 18f)
                } else {
                    Log.d(TAG, "Current location is null. Using defaults.")
                    Log.e(TAG, "Exception: %s", task.exception)
                }
            }
            // Configure the Google Map
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
                uiSettings = uiSettings,
                onMapClick = { viewModel.updateSelectedLocation(it) },
                onMyLocationClick = {
                    viewModel.updateSelectedLocation(LatLng(it.latitude, it.longitude))
                },
            ) {
                val remarks = viewModel.remarks.collectAsState(emptyList()).value
                for(remark in remarks) {
                    MarkerNote(
                        remark = remark,
                    )
                }
                uiState.selectedLocation?.let {
                    viewModel.updateTemporaryRemark(
                        Remark(
                            latitude = uiState.selectedLocation!!.latitude,
                            longitude = uiState.selectedLocation!!.longitude
                        )
                    )
                    if(uiState.temporaryRemark != null) {
                        MarkerNote(uiState.temporaryRemark!!)
                    }
                }
            }

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

@Composable
@GoogleMapComposable
fun MarkerNote(
    remark: Remark,
) {
    MarkerInfoWindowContent(
        state = MarkerState(position = LatLng(remark.latitude, remark.longitude)),
        title = remark.userId,
        snippet = remark.note,
    ) { marker ->
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                modifier = Modifier.padding(top = 6.dp),
                text = marker.title ?: "",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "(${remark.latitude}, ${remark.longitude}",
                color = Color.Gray
            )
            Text(
                text = remark.note,
            )
        }
    }
}

@Preview
@Composable
fun MapScreenPreview() {
}