package com.alimrasid.tajwidplusver11.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("v2/surat")
    fun getSurat(): Call<SuratResponse>

    @GET("v2/surat/{nomor}")
    fun getSuratDetail(@Path("nomor") nomor: Int): Call<SuratDetailResponse>

    @GET("doa")
    fun getDoaList(): Call<List<Doa>>

    @GET("doa/{id}")
    fun getDoaDetail(@Path("id") id: Int): Call<Doa>
}