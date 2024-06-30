package com.alimrasid.tajwidplusver11.activity

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alimrasid.tajwidplusver11.R

class AyatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayat)

        val teksArabTextView: TextView = findViewById(R.id.teksArabText)
        val teksLatinTextView: TextView = findViewById(R.id.teksLatinText)
        val teksIndonesiaTextView: TextView = findViewById(R.id.teksIndonesiaText)

        val tajwidTextView: TextView = findViewById(R.id.tajwidTextView)
        val detectTajwidButton: Button = findViewById(R.id.detectTajwidButton)


        val teksArab = intent.getStringExtra("teksArab")
        val teksLatin = intent.getStringExtra("teksLatin")
        val teksIndonesia = intent.getStringExtra("teksIndonesia")

        teksArabTextView.text = teksArab
        teksLatinTextView.text = teksLatin
        teksIndonesiaTextView.text = teksIndonesia

        detectTajwidButton.setOnClickListener {
            teksArab?.let { text ->
                val (highlightedText, tajwidNames) = highlightTajwid(text)
                teksArabTextView.text = highlightedText
                tajwidTextView.text = tajwidNames.joinToString("\n")
            }
        }
    }

    private fun highlightTajwid(text: String): Pair<SpannableStringBuilder, List<String>> {
        val spannableStringBuilder = SpannableStringBuilder(text)
        val tajwidPatterns = listOf(
            "(نْ|ً|ٍ|ٌ)(ء|ح|خ|ع|غ|هـ)" to "Izhar",
            "(نْ|ً|ٍ|ٌ)(ي|ن|م|و)" to "Idgham",
            "(نْ|ً|ٍ|ٌ)ب" to "Iqlab",
            "(نْ|ً|ٍ|ٌ)(ت|ث|ج|د|ذ|ز|س|ش|ص|ض|ط|ظ|ف|ق|ك)" to "Ikhfa"
        )
        val colors = listOf(
            Color.GREEN, // Izhar
            Color.BLUE, // Idgham
            Color.MAGENTA, // Iqlab
            Color.RED // Ikhfa
        )
        val tajwidNames = mutableListOf<String>()

        for ((index, pattern) in tajwidPatterns.withIndex()) {
            val regex = Regex(pattern.first)
            val matches = regex.findAll(text)
            var offset = 0

            for (match in matches) {
                val tajwidName = pattern.second
                val start = match.range.first + offset
                val end = match.range.last + 1 + offset

                spannableStringBuilder.setSpan(
                    ForegroundColorSpan(colors[index]),
                    start,
                    end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                tajwidNames.add("Position: ${match.range.first}-${match.range.last}, Tajwid: $tajwidName")

                offset += tajwidName.length
            }
        }

        return Pair(spannableStringBuilder, tajwidNames)
    }
}