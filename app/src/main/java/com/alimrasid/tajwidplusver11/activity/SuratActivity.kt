package com.alimrasid.tajwidplusver11.activity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alimrasid.tajwidplusver11.R
import com.alimrasid.tajwidplusver11.adapter.AyatAdapter
import com.alimrasid.tajwidplusver11.api.RetrofitClient
import com.alimrasid.tajwidplusver11.api.SuratDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SuratActivity : AppCompatActivity() {

    private lateinit var tvSurahName: TextView
    private lateinit var tvSurahDescription: TextView
    private lateinit var rvAyatList: RecyclerView
    private lateinit var ayatAdapter: AyatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surat)

        tvSurahName = findViewById(R.id.tvSurahName)
//        tvSurahDescription = findViewById(R.id.tvSurahDescription)
        rvAyatList = findViewById(R.id.rvAyatList)
        rvAyatList.layoutManager = LinearLayoutManager(this)

        val nomorSurah = intent.getIntExtra("SURAH_NUMBER", 1)

        val api = RetrofitClient.apiService
        api.getSurahDetail(nomorSurah).enqueue(object : Callback<SuratDetail> {
            override fun onResponse(call: Call<SuratDetail>, response: Response<SuratDetail>) {
                if (response.isSuccessful) {
                    val surahDetail = response.body()
                    surahDetail?.let {
                        tvSurahName.text = it.nama_latin
//                        tvSurahDescription.text = it.deskripsi
                        ayatAdapter = AyatAdapter(it.ayat)
                        rvAyatList.adapter = ayatAdapter
                    }
                } else {
                    Log.e("SurahDetailActivity", "Request failed with code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<SuratDetail>, t: Throwable) {
                Log.e("SurahDetailActivity", "Request failed with error: ${t.message}")
            }
        })
    }
}