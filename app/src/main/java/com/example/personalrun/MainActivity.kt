package com.example.personalrun

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.personalrun.activity.presentation.ActivityViewModel
import com.example.personalrun.statistics.presentation.StatisticsViewModel
import com.example.personalrun.ui.theme.PersonalRunTheme

class MainActivity : ComponentActivity() {
    private val stravaApiService: ActivityViewModel by viewModels { ActivityViewModel.Factory }
    private val statisticsService: StatisticsViewModel by viewModels{StatisticsViewModel.Factory}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PersonalRunTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    PersonalRun(
                        stravaApiService = ActivityViewModel,
                        statisticsService = StatisticsViewModel
                    )

                }
            }
        }
    }
}




