package com.example.personalrun

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface StravaApiService {

    @GET("athlete/activities")
    fun getAllActivities(): Call<List<StravaDto>>

    @GET("athletes/{id}/stats")
    fun getAthletesActivities(@Path("id")  athleteId: Int): Call<AthletesDto>
}
