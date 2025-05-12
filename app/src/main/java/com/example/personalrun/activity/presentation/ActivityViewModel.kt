package com.example.personalrun.activity.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.personalrun.common.data.RetrofitClient
import com.example.personalrun.activity.data.StravaApiService
import com.example.personalrun.common.model.StravaDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ActivityViewModel (

    private val stravaApiService: StravaApiService
): ViewModel() {


    private val _activity = MutableStateFlow<List<StravaDto>>(emptyList())
    val activity: StateFlow<List<StravaDto>> = _activity


    fun fetchActivities(type: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = stravaApiService.getAllActivities()
            if (response.isSuccessful) {
                val activities = response.body()
                if (activities != null) {
                    _activity.value = activities
                    // Filtra as atividades pelo tipo recebido
                    _activity.value = activities.filter { it.type.equals(type, ignoreCase = true) }
                }
            } else {
                Log.d("Strava", "Request Error:: ${response.errorBody()?.string()}")
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
                val stravaApiService =
                    RetrofitClient.retrofitInstance.create(StravaApiService::class.java)
                return ActivityViewModel(
                    stravaApiService
                ) as T
            }
        }
    }

}