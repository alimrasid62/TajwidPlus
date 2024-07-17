package com.alimrasid.tajwidplusver11.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alimrasid.tajwidplusver11.R
import com.alimrasid.tajwidplusver11.activity.SuratActivity
import com.alimrasid.tajwidplusver11.api.Surat

class SuratAdapter (private val surahList: List<Surat>) : RecyclerView.Adapter<SuratAdapter.SurahViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_surat, parent, false)
        return SurahViewHolder(view)
    }

    override fun onBindViewHolder(holder: SurahViewHolder, position: Int) {
        val surah = surahList[position]
        holder.bind(surah)
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, SuratActivity::class.java)
            intent.putExtra("SURAH_NUMBER", surah.nomor)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = surahList.size

    class SurahViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvSurahName: TextView = itemView.findViewById(R.id.namaLatinTextView)
        private val tvSurahLatin: TextView = itemView.findViewById(R.id.artiTextView)

        fun bind(surah: Surat) {
            tvSurahName.text = surah.nama
            tvSurahLatin.text = surah.nama_latin
        }
    }
}