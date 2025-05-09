package com.example.personalrun

import com.google.gson.annotations.SerializedName

data class StravaDto(
val id: Long,
val name: String,
val distance: Double,
val moving_time: Int,
val total_elevation_gain: Double,
val type: String,
val start_date: String,
val average_speed: Double,
val max_speed:Double,
val average_cadence: Double,
val average_heartrate: Double,
val max_heartrate: Int
)

data class AthletesDto(
    @SerializedName("all_run_totals")
    val allRunTotals: RunTotals
)

data class RunTotals(
    @SerializedName("count")
    val count: Int,

    @SerializedName("distance")
    val distance: Float,

    @SerializedName("moving_time")
    val movingTime: Int
)