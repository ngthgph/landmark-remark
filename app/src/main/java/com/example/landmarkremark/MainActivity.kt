package com.example.landmarkremark

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.landmarkremark.ui.LandmarkRemarkApp
import com.example.landmarkremark.ui.theme.LandmarkRemarkTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LandmarkRemarkTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LandmarkRemarkApp()
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Preview() {
    LandmarkRemarkTheme {
        LandmarkRemarkApp()
    }
}