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
import com.alimrasid.tajwidplusver11.api.ApiService
import com.alimrasid.tajwidplusver11.api.RetrofitClient
import com.alimrasid.tajwidplusver11.api.SuratResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuranActivity : AppCompatActivity(){

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quran)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchSuratData()
    }

    private fun fetchSuratData() {
        val apiService = RetrofitClient.instance.create(ApiService::class.java)
        apiService.getSurat().enqueue(object : Callback<SuratResponse> {
            override fun onResponse(call: Call<SuratResponse>, response: Response<SuratResponse>) {
                if (response.isSuccessful) {
                    val suratList = response.body()?.data
                    suratList?.let {
                        recyclerView.adapter = SuratAdapter(it) { surat ->
                            val intent = Intent(this@QuranActivity, SuratActivity::class.java)
                            intent.putExtra("nomorSurat", surat.nomor)
                            startActivity(intent)
                        }
                    }
                } else {
                    Toast.makeText(
                        this@QuranActivity,
                        "Failed to retrieve data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<SuratResponse>, t: Throwable) {
                Toast.makeText(this@QuranActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}