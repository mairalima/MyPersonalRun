package com.example.personalrun

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//dados buscando da Api, depois fazer o filtro conforme o dado

@Composable
 fun ActivityDadosScreen(type: String){

    var actActivities by remember { mutableStateOf<List<StravaDto>>(emptyList()) }
    var filteredActivities by remember { mutableStateOf<List<StravaDto>>(emptyList()) }

    val apiService =
        RetrofitClient.retrofitInstance.create(StravaApiService::class.java)
    val allActivities = apiService.getAllActivities()


    allActivities.enqueue(object : Callback<List<StravaDto>> {
        override fun onResponse(
            call: Call<List<StravaDto>>,
            response: Response<List<StravaDto>>
        ) {
            if (response.isSuccessful) {
                val activities = response.body()
                if (activities != null) {
                    actActivities = activities
                    // Filtra as atividades pelo tipo recebido
                    filteredActivities = activities.filter { it.type.equals(type, ignoreCase = true)}
                }
            } else {
                Log.d("Strava", "Request Error:: ${response.errorBody()?.string()}")
            }
        }

        override fun onFailure(call: Call<List<StravaDto>>, t: Throwable) {
            Log.d("Strava", "Network Error:: ${t.message}")
        }
    })

    ActivityList(
        filteredActivities
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