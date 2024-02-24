package com.example.landmarkremark.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun LandmarkRemarkApp() {

    val viewModel: LandmarkViewModel = viewModel(factory = LandmarkViewModel.Factory)
    MapScreen(
        viewModel = viewModel,
        uiState = viewModel.uiState.collectAsState().value
    )
}