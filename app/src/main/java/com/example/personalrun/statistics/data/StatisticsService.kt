package com.example.personalrun.statistics.data

import com.example.personalrun.common.model.AthletesDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StatisticsService {

    @GET("athletes/{id}/stats")
    suspend fun getAthletesActivities(@Path("id")  athleteId: Int): Response<AthletesDto>
}