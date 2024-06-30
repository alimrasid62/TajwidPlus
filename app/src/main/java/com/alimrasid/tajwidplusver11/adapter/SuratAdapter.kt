package com.alimrasid.tajwidplusver11.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alimrasid.tajwidplusver11.R
import com.alimrasid.tajwidplusver11.api.Surat

class SuratAdapter (private val suratList: List<Surat>,
                    private val onItemClick: (Surat) -> Unit) : RecyclerView.Adapter<SuratAdapter.SuratViewHolder>() {

    class SuratViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val namaLatinTextView: TextView = itemView.findViewById(R.id.namaLatinTextView)
        private val artiTextView: TextView = itemView.findViewById(R.id.artiTextView)
        private val nomorTextView: TextView = itemView.findViewById(R.id.nomorTextView)

        fun bind(surat: Surat, onItemClick: (Surat) -> Unit) {
            namaLatinTextView.text = surat.namaLatin
            artiTextView.text = surat.arti
            nomorTextView.text = surat.nomor.toString()
            itemView.setOnClickListener {
                onItemClick(surat)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuratViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_surat, parent, false)
        return SuratViewHolder(view)
    }

    override fun onBindViewHolder(holder: SuratViewHolder, position: Int) {
        val surat = suratList[position]
        holder.bind(surat, onItemClick)
    }

    override fun getItemCount(): Int {
        return suratList.size
    }
}