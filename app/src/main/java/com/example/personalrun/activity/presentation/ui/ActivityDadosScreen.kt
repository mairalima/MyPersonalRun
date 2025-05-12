package com.example.personalrun.activity.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.personalrun.activity.presentation.ActivityViewModel
import com.example.personalrun.common.model.StravaDto

//dados buscando da Api, depois fazer o filtro conforme o dado

@Composable
 fun ActivityDadosScreen(type: String){
    val viewModel: ActivityViewModel = viewModel(factory = ActivityViewModel.Factory)

    val activities by viewModel.activity.collectAsState()

    LaunchedEffect(type) {
        viewModel.fetchActivities(type)
    }

    ActivityList(
        activities
    )


}

@Composable
fun ActivityList(activityList: List<StravaDto>) {
    LazyColumn {
        items(activityList) { activity ->
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Name: ${activity.name}")
                Text(text = "Distance  %.2f KM" .format(activity.distance / 1000))
                val hours = activity.moving_time / 3600
                val minutes = (activity.moving_time % 3600) / 60
                Text(text = "Moving Time: ${hours}h ${minutes}min")
                val paceInSeconds = 1000 / activity.average_speed // Converte para segundos por km
                val minu = (paceInSeconds / 60).toInt() // Minutos inteiros
                val seconds = (paceInSeconds % 60).toInt() // Segundos restantes
                val formattedPace = String.format("%d:%02d /km", minu, seconds)
                Text(text = "Pace: $formattedPace")
                Text(text = "Elevation Gain: ${activity.total_elevation_gain}")
                Text(text = "Type: ${activity.type}")
            }
        }
    }
}