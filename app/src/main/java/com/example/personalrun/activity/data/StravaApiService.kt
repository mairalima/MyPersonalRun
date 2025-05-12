package com.example.personalrun.activity.data

import com.example.personalrun.common.model.AthletesDto
import com.example.personalrun.common.model.StravaDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StravaApiService {

    @GET("athlete/activities")
    suspend fun getAllActivities(): Response<List<StravaDto>>


}
