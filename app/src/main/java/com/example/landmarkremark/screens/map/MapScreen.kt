package com.example.landmarkremark.screens.map

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.landmarkremark.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    onAccount: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val viewModel: MapViewModel = viewModel(factory = MapViewModel.Factory)

        val uiState = viewModel.uiState.collectAsState().value
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false,
        )
    )
    val scope = rememberCoroutineScope()

    EntryBottomSheet(
        sheetScaffoldState = bottomSheetScaffoldState,
        onCancel = {
            scope.launch {
                bottomSheetScaffoldState.bottomSheetState.hide()
            }
        },
        onSave = {
            //ViewModel.saveNote()
            scope.launch {
                bottomSheetScaffoldState.bottomSheetState.hide()
            }
        },
        modifier = modifier
    ) {
        Scaffold(
            topBar ={
                    AppBar(
                        isEdit = uiState.selectedLocation != null,
                        onEditNote = {
                            //ViewModel.updateCurrentNote
                            scope.launch {
                                bottomSheetScaffoldState.bottomSheetState.expand()
                            }
                        },
                        onAccount = onAccount
                    )
            }
        ) { contentPadding ->
            GoogleMapWindow(
                viewModel = viewModel,
                uiState = uiState,
                modifier = Modifier.padding(contentPadding)
            )
        }
    }
}
@Composable
fun AppBar(
    isEdit: Boolean = false,
    onEditNote: () -> Unit,
    onAccount: () -> Unit,
) {
    Row{
        IconButton(
            onClick = onAccount,
        ){
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = stringResource(R.string.account),
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = onEditNote,
            enabled = isEdit
        ){
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = stringResource(R.string.edit_note),
            )
        }
    }
}