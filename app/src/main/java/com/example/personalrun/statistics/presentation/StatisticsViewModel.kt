package com.example.personalrun.statistics.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.personalrun.activity.data.StravaApiService
import com.example.personalrun.activity.presentation.ActivityViewModel
import com.example.personalrun.common.data.RetrofitClient
import com.example.personalrun.common.model.AthletesDto
import com.example.personalrun.statistics.data.StatisticsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StatisticsViewModel (

    private val statisticsService: StatisticsService

) : ViewModel() {

    private val _athlete = MutableStateFlow<AthletesDto?>(null)
    val athlete: StateFlow<AthletesDto?> = _athlete


    fun fetchAthleteStatistics (athleteId : Int){
        if (_athlete.value == null){
        viewModelScope.launch(Dispatchers.IO) {
            val response = statisticsService.getAthletesActivities(athleteId)
            if (response.isSuccessful) {
                _athlete.value = response.body()
            } else {
                Log.d("StatisticsViewModel", "Request Error: Code ${response.code()} - ${response.message()}")
                        }
                    }

        }

    }


    companion object : ViewModelProvider.Factory {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val statisticsService =
                    RetrofitClient.retrofitInstance.create(StatisticsService::class.java)
                return StatisticsViewModel(
                    statisticsService
                ) as T
            }
        }
    }

}