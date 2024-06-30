package com.alimrasid.tajwidplusver11.activity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alimrasid.tajwidplusver11.R
import com.alimrasid.tajwidplusver11.adapter.AyatAdapter
import com.alimrasid.tajwidplusver11.api.ApiService
import com.alimrasid.tajwidplusver11.api.RetrofitClient
import com.alimrasid.tajwidplusver11.api.SuratDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SuratActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var namaLatinTextView: TextView
    private lateinit var artiTextView: TextView
    private lateinit var deskripsiTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surat)

        namaLatinTextView = findViewById(R.id.namaLatinTextView)
        artiTextView = findViewById(R.id.artiTextView)
        deskripsiTextView = findViewById(R.id.deskripsiTextView)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val nomorSurat = intent.getIntExtra("nomorSurat", -1)
        if (nomorSurat != -1) {
            fetchSuratDetail(nomorSurat)
        }
    }
    private fun fetchSuratDetail(nomor: Int) {
        val apiService = RetrofitClient.instance.create(ApiService::class.java)
        apiService.getSuratDetail(nomor).enqueue(object : Callback<SuratDetailResponse> {
            override fun onResponse(call: Call<SuratDetailResponse>, response: Response<SuratDetailResponse>) {
                if (response.isSuccessful) {
                    val suratDetail = response.body()?.data
                    suratDetail?.let {
                        namaLatinTextView.text = it.namaLatin
                        artiTextView.text = it.arti
                        deskripsiTextView.text = it.deskripsi
                        recyclerView.adapter = AyatAdapter(it.ayat) { ayat ->
                            val intent = Intent(this@SuratActivity, AyatActivity::class.java)
                            intent.putExtra("teksArab", ayat.teksArab)
                            intent.putExtra("teksLatin", ayat.teksLatin)
                            intent.putExtra("teksIndonesia", ayat.teksIndonesia)
                            startActivity(intent)
                        }
                    }
                } else {
                    Toast.makeText(this@SuratActivity, "Failed to retrieve data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SuratDetailResponse>, t: Throwable) {
                Toast.makeText(this@SuratActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}