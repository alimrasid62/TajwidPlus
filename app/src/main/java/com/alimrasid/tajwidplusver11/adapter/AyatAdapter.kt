package com.alimrasid.tajwidplusver11.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alimrasid.tajwidplusver11.R
import com.alimrasid.tajwidplusver11.activity.AyatActivity
import com.alimrasid.tajwidplusver11.api.Ayat

class AyatAdapter(private val ayatList: List<Ayat>) : RecyclerView.Adapter<AyatAdapter.AyatViewHolder>() {

    class AyatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ayatArabic: TextView = itemView.findViewById(R.id.tvAyatArabic)
        val ayatTranslation: TextView = itemView.findViewById(R.id.tvAyatTranslation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ayat, parent, false)
        return AyatViewHolder(view)
    }

    override fun onBindViewHolder(holder: AyatViewHolder, position: Int) {
        val ayat = ayatList[position]
        holder.ayatArabic.text = ayat.ar
        holder.ayatTranslation.text = ayat.idn

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, AyatActivity::class.java).apply {
                putExtra("ayat_arabic", ayat.ar)
                putExtra("ayat_translation", ayat.idn)
                putExtra("ayat_transliteration", ayat.tr)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return ayatList.size
    }
}