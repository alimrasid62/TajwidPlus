package com.alimrasid.tajwidplusver11.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alimrasid.tajwidplusver11.R
import com.alimrasid.tajwidplusver11.api.Ayat

class AyatAdapter (private val ayatList: List<Ayat>,
                   private val onItemClick: (Ayat) -> Unit) : RecyclerView.Adapter<AyatAdapter.AyatViewHolder>() {

    class AyatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val teksArabTextView: TextView = itemView.findViewById(R.id.teksArabTextView)
        private val teksLatinTextView: TextView = itemView.findViewById(R.id.teksLatinTextView)
        private val teksIndonesiaTextView: TextView =
            itemView.findViewById(R.id.teksIndonesiaTextView)

        fun bind(ayat: Ayat, onItemClick: (Ayat) -> Unit) {
            teksArabTextView.text = ayat.teksArab
            teksLatinTextView.text = ayat.teksLatin
            teksIndonesiaTextView.text = ayat.teksIndonesia
            itemView.setOnClickListener {
                onItemClick(ayat)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ayat, parent, false)
        return AyatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ayatList.size
    }

    override fun onBindViewHolder(holder: AyatViewHolder, position: Int) {
        val ayat = ayatList[position]
        holder.bind(ayat, onItemClick)
    }
}