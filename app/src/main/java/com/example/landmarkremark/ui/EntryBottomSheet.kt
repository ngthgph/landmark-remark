package com.example.landmarkremark.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import com.example.landmarkremark.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryBottomSheet(
    sheetScaffoldState: BottomSheetScaffoldState,
    onCancel: () -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
    note: String? = null,
    content: @Composable () -> Unit,
) {
    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = sheetScaffoldState,
        sheetContent = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(id = R.string.note_title),//"(${position.latitude}, ${position.longitude})",
                    fontWeight = FontWeight.Bold,
                )
                Divider()
                TextField(
                    value = note ?: "",
                    placeholder = { Text(stringResource(R.string.note_content), color = Color.Gray) },
                    onValueChange = {
                        //ViewModel::updateCurrentNote
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                )
                Row {
                    IconButton(onClick = onSave) {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = stringResource(R.string.save_note),
                            tint = Color.Gray
                        )
                    }
                    IconButton(onClick = onCancel) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = stringResource(R.string.delete_note),
                            tint = Color.Gray
                        )
                    }
                }
            }
        }
    ) {
        content()
    }
}