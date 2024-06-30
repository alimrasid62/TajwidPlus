package com.alimrasid.tajwidplusver11.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alimrasid.tajwidplusver11.R
import com.alimrasid.tajwidplusver11.activity.DetaiDoaActivity
import com.alimrasid.tajwidplusver11.api.Doa

class DoaAdapter(private val doaList: List<Doa>) : RecyclerView.Adapter<DoaAdapter.DoaViewHolder>() {

    class DoaViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvJudul: TextView = itemView.findViewById(R.id.tvJudul)
        val tvLatin: TextView = itemView.findViewById(R.id.tvLatin)
        val tvArab: TextView = itemView.findViewById(R.id.tvArab)
        val tvTerjemah: TextView = itemView.findViewById(R.id.tvTerjemah)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_doa, parent, false)
        return DoaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return doaList.size
    }

    override fun onBindViewHolder(holder: DoaViewHolder, position: Int) {
        val doa = doaList[position]
        holder.tvJudul.text = doa.judul
        holder.tvLatin.text = doa.latin
        holder.tvArab.text = doa.arab
        holder.tvTerjemah.text = doa.terjemah

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetaiDoaActivity::class.java)
            intent.putExtra("DOA_ID", doa.id)
            context.startActivity(intent)
        }
    }
}