package com.alimrasid.tajwidplusver11.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alimrasid.tajwidplusver11.R
import com.alimrasid.tajwidplusver11.adapter.SuratAdapter
import com.alimrasid.tajwidplusver11.api.ApiClient
import com.alimrasid.tajwidplusver11.api.ApiService
import com.alimrasid.tajwidplusver11.api.RetrofitClient
import com.alimrasid.tajwidplusver11.api.Surat
import com.alimrasid.tajwidplusver11.api.SuratResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuranActivity : AppCompatActivity(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var surahAdapter: SuratAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quran)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchSuratData()
    }

    private fun fetchSuratData() {
        val call = RetrofitClient.apiService.getSurahList()
        call.enqueue(object : Callback<List<Surat>> {
            override fun onResponse(call: Call<List<Surat>>, response: Response<List<Surat>>) {
                if (response.isSuccessful) {
                    val surahList = response.body() ?: emptyList()
                    surahAdapter = SuratAdapter(surahList)
                    recyclerView.adapter = surahAdapter
                } else {
                    Toast.makeText(this@QuranActivity, "Failed to get data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Surat>>, t: Throwable) {
                Toast.makeText(this@QuranActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}