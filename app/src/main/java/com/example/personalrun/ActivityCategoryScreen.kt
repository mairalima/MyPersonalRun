package com.example.personalrun

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ActivityCategoryScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "My Activities",
            modifier = Modifier.padding(bottom = 16.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))

        val activities = listOf(
            Triple("Run", "Running", R.drawable.run),
            Triple("WeightTraining", "Weight Training", R.drawable.gym),
            Triple("Ride", "Cycling", R.drawable.pedal),
            Triple("Pool", "Swimming", R.drawable.pool_1)
        )

        // Itera sobre a lista e cria os cards
        activities.forEach { (type, name, icon) ->
            ImageCard(
                activityType = type,
                activityName = name, // Passe o nome correto
                icon = icon
            ) {
                navController.navigate(route = "activityList/$type")
            }
        }
    }
}
@Composable
fun ImageCard(activityType: String, activityName: String,  icon: Int, onClick: () -> Unit,) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Icon(
            painter = painterResource(id = icon) , // Altere o ícone conforme necessário
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )

            Spacer(modifier = Modifier.size(16.dp))
        Column {
                Text(text= activityName)
                Text(
                    text = when (activityType) {
                        "Run" -> "Track your runs and walks"
                        "WeightTraining" -> "Track your weightlifting"
                        "Ride" -> "Track your bike rides"
                        "Pool" -> "Track your swim workouts"
                        else -> "Other activities"
                    },
                )
            }

        }
    }
