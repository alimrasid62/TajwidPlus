package com.alimrasid.tajwidplusver11.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alimrasid.tajwidplusver11.R
import com.alimrasid.tajwidplusver11.adapter.DoaAdapter
import com.alimrasid.tajwidplusver11.adapter.SuratAdapter
import com.alimrasid.tajwidplusver11.api.ApiClient
import com.alimrasid.tajwidplusver11.api.ApiService
import com.alimrasid.tajwidplusver11.api.Doa
import com.alimrasid.tajwidplusver11.api.RetrofitClient
import com.alimrasid.tajwidplusver11.api.SuratResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DoaActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var doaAdapter: DoaAdapter
    private lateinit var searchView: SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doa)

        recyclerView = findViewById(R.id.recyclerView)
//        searchView = findViewById(R.id.searchView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchDoaData()

//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                doaAdapter.filter.filter(newText)
//                return false
//            }
//        })
    }


    private fun fetchDoaData() {
        val call = ApiClient.doaApiService.getDoaList()
        call.enqueue(object : Callback<List<Doa>> {
            override fun onResponse(call: Call<List<Doa>>, response: Response<List<Doa>>) {
                if (response.isSuccessful) {
                    val doaList = response.body() ?: emptyList()
                    doaAdapter = DoaAdapter(doaList)
                    recyclerView.adapter = doaAdapter
                } else {
                    Log.e("MainActivity", "Failed to retrieve data")
                }
            }

            override fun onFailure(call: Call<List<Doa>>, t: Throwable) {
                Log.e("MainActivity", "Error: ${t.message}")
            }
        })
    }



}