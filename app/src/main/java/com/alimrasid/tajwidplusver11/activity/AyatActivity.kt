package com.alimrasid.tajwidplusver11.activity

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.alimrasid.tajwidplusver11.R
import org.json.JSONArray



class AyatActivity : AppCompatActivity() {

    private lateinit var tajwidData: JSONArray

    private lateinit var tvAyatArabic: TextView
    private lateinit var tvAyatTranslation: TextView
    private lateinit var tvAyatTransliteration: TextView
    private lateinit var tvTajwidExplanation: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayat)

        tvAyatArabic = findViewById(R.id.tvAyatArabic)
        tvAyatTranslation = findViewById(R.id.tvAyatTranslation)
//        tvAyatTransliteration = findViewById(R.id.tvAyatTransliteration)
        tvTajwidExplanation = findViewById(R.id.tvTajwidExplanation)
        val detectTajwidButton: Button = findViewById(R.id.detectTajwidButton)


        val ayatAr = intent.getStringExtra("ayat_arabic")
        val ayatIdn = intent.getStringExtra("ayat_translation")
        val ayatTr = intent.getStringExtra("ayat_transliteration")

        tvAyatArabic.text = ayatAr
        tvAyatTranslation.text = ayatIdn
//        tvAyatTransliteration.text = ayatTr


        detectTajwidButton.setOnClickListener {
            ayatAr?.let { text ->
                val (highlightedText, tajwidNames) = highlightTajwid(text)
                tvAyatArabic.text = highlightedText
                tvTajwidExplanation.text = tajwidNames.joinToString("\n")
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

                // Append tajwid name to the list
                tajwidNames.add("Position: ${match.range.first}-${match.range.last}, Tajwid: $tajwidName")

                offset += tajwidName.length
            }
        }

        return Pair(spannableStringBuilder, tajwidNames)
    }

}
