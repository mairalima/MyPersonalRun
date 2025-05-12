package com.example.personalrun.common.data

import com.example.personalrun.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {

    private const val BASE_URL = "https://www.strava.com/api/v3/"
    private val httpClient: OkHttpClient
        get(){
            val clientBuilder = OkHttpClient.Builder()
            val token = BuildConfig.API_KEY

            clientBuilder.addInterceptor{ chain ->
                val original: Request = chain.request()
                val requestBuilder: Request.Builder = original.newBuilder()
                    .header("Authorization", "Bearer $token")
                val request: Request = requestBuilder.build()
                chain.proceed(request)

            }
            return clientBuilder.build()

        }

   val retrofitInstance : Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
       .client(httpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

}