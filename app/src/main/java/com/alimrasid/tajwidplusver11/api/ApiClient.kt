package com.alimrasid.tajwidplusver11.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "https://open-api.my.id/api/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val doaApiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}