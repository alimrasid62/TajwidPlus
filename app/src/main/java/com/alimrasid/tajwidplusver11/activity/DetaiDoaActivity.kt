package com.alimrasid.tajwidplusver11.activity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alimrasid.tajwidplusver11.R
import com.alimrasid.tajwidplusver11.api.ApiClient
import com.alimrasid.tajwidplusver11.api.Doa
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetaiDoaActivity : AppCompatActivity() {

    private lateinit var tvJudul: TextView
    private lateinit var tvLatin: TextView
    private lateinit var tvArab: TextView
    private lateinit var tvTerjemah: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detai_doa)

        tvJudul = findViewById(R.id.tvJudulDoa)
        tvLatin = findViewById(R.id.tvLatinDoa)
        tvArab = findViewById(R.id.tvArabDoa)
        tvTerjemah = findViewById(R.id.tvTerjemahDoa)

        val doaId = intent.getIntExtra("DOA_ID", 0)
        fetchDoaDetail(doaId)
    }

    private fun fetchDoaDetail(id: Int) {
        val call = ApiClient.doaApiService.getDoaDetail(id)
        call.enqueue(object : Callback<Doa> {
            override fun onResponse(call: Call<Doa>, response: Response<Doa>) {
                if (response.isSuccessful) {
                    val doa = response.body()
                    doa?.let {
                        tvJudul.text = it.judul
                        tvLatin.text = it.latin
                        tvArab.text = it.arab
                        tvTerjemah.text = it.terjemah
                    }
                } else {
                    Log.e("DoaDetailActivity", "Failed to retrieve data")
                }
            }

            override fun onFailure(call: Call<Doa>, t: Throwable) {
                Log.e("DoaDetailActivity", "Error: ${t.message}")
            }
        })
    }
}