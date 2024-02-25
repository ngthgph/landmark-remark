package com.example.landmarkremark.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.landmarkremark.R
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMapComposable
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.MarkerState

@Composable
@GoogleMapComposable
fun MarkerNote(
    position: LatLng,
    note: String? = null,
) {
    Column {
        MarkerInfoWindowContent(
            state = MarkerState(position = position),
            title = stringResource(id = R.string.note_title),
            snippet = "(${position.latitude}, ${position.longitude})",
        ) {marker ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    modifier = Modifier.padding(top = 6.dp),
                    text = marker.title ?: stringResource(id = R.string.note_title),
                    fontWeight = FontWeight.Bold
                )
                Text(text = note?: "(${position.latitude}, ${position.longitude})")
            }
        }
    }
}