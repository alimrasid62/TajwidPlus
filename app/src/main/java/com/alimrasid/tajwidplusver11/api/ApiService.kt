package com.alimrasid.tajwidplusver11.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

//    @GET("v2/surat")
//    fun getSurat(): Call<SuratResponse>

    @GET("api/quran/surah")
    fun getSurahList(): Call<List<Surat>>

    @GET("api/quran/surah/{nomor}")
    fun getSurahDetail(@Path("nomor") nomor: Int): Call<SuratDetail>

    @GET("doa")
    fun getDoaList(): Call<List<Doa>>

    @GET("doa/{id}")
    fun getDoaDetail(@Path("id") id: Int): Call<Doa>
}