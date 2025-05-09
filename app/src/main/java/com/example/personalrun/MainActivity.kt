package com.example.personalrun

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.personalrun.ui.theme.PersonalRunTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PersonalRunTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    PersonalRun()

                }
            }
        }
    }
}




