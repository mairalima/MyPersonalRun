package com.example.personalrun

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun Perfil() {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically)
        {
            Image(
                modifier = Modifier
                    .size(60.dp)
                    .padding(5.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.White, CircleShape),
                contentScale = ContentScale.FillWidth,
                painter = painterResource(id = R.drawable.run_night),
                contentDescription = "Perfil"
            )
            Spacer(modifier = Modifier.size(16.dp))
            Column {
                Text(
                    text = "Hi, Maira",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF4CAF50)
                )
            }
        }


    }

}

@Composable
fun AthleteStats(athleteId: Int) {
    var athletesDto by remember { mutableStateOf<AthletesDto?>(null) }
    val apiService =
        RetrofitClient.retrofitInstance.create(StravaApiService::class.java)


    apiService.getAthletesActivities(athleteId).enqueue(
        object : Callback<AthletesDto> {
            override fun onResponse(call: Call<AthletesDto>, response: Response<AthletesDto>) {
                if (response.isSuccessful) {
                    athletesDto = response.body()
                } else {
                    Log.d("Strava", "Request Error: Code ${response.code()} - ${response.message()}")
                }
            }

            override fun onFailure(call: Call<AthletesDto>, t: Throwable) {
                Log.d("Strava", "Network Error:: ${t.message}")
            }

        }
    )
    athletesDto?.let {
        ActivityStatistics(it)
    }
}

    @Composable
    fun ActivityStatistics(athletes: AthletesDto) {
        Card(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color(0xFF4CAF50)),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Your Statistics",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Activities ${athletes.allRunTotals.count} ")
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val distanceInKm = athletes.allRunTotals.distance / 1000
                    val distanceDisplay = "%,.2f KM".format(distanceInKm)
                    Text(text = "Distance $distanceDisplay")
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val hours = athletes.allRunTotals.movingTime / 3600
                    val minutes = (athletes.allRunTotals.movingTime % 3600) / 60
                    Text(text = "Time: ${hours}h ${minutes}min")

                }
            }
        }
    }

    @Composable
    fun Planning() {

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Training Plan",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "View and Manage your plans",
                    fontSize = 15.sp,
                    color = Color.White,
                )
                Button(
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ){
                    Text(
                        text = " Open ",
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
        }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun HistoryActivity(navController: NavController) {

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Activity History",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(text ="Track your past Workouts",
                    fontSize = 15.sp,
                    color = Color.White
                )
                Button(
                    onClick = {
                        navController.navigate(route = "activity_category")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ){
                    Text(
                        text = " View",
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
        }


    @Composable
    fun ProfileWithStatistics(navController: NavHostController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Chama os composables Perfil e ActivityStatistics
            Perfil()
            AthleteStats(athleteId = 103093325)
            Planning()
            HistoryActivity(navController)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ProfileWithStatisticsPreview() {
        val navController = rememberNavController()
        ProfileWithStatistics(navController)
    }
