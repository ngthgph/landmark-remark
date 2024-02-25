package com.example.landmarkremark.screens.map

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import com.example.landmarkremark.R
import com.example.landmarkremark.model.Remark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryBottomSheet(
    sheetScaffoldState: BottomSheetScaffoldState,
    onCancel: () -> Unit,
    onSave: (String) -> Unit,
    modifier: Modifier = Modifier,
    remark: Remark? = null,
    content: @Composable () -> Unit,
) {
    var note by remember { mutableStateOf(remark?.note?: "") }
    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = sheetScaffoldState,
        sheetContent = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (remark != null) {
                    Text(
                        text = remark.userId,//"(${position.latitude}, ${position.longitude})",
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "(${remark.latitude}, ${remark.longitude}",
                        color = Color.Gray
                    )
                }
                Divider()
                TextField(
                    value = note,
                    placeholder = { Text(stringResource(R.string.note), color = Color.Gray) },
                    onValueChange = { note = it },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                )
                Row {
                    IconButton(
                        onClick = { onSave(note) }
                    ) {
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