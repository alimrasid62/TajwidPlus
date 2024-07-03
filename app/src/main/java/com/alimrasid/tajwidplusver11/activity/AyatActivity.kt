package com.alimrasid.tajwidplusver11.activity

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.SubscriptSpan
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.alimrasid.tajwidplusver11.R
import org.json.JSONArray
import java.io.InputStream

class AyatActivity : AppCompatActivity() {

    private lateinit var tajwidData: JSONArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayat)

        val teksArabTextView: TextView = findViewById(R.id.teksArabText)
        val teksLatinTextView: TextView = findViewById(R.id.teksLatinText)
        val teksIndonesiaTextView: TextView = findViewById(R.id.teksIndonesiaText)
        val detectTajwidButton: Button = findViewById(R.id.detectTajwidButton)

        val teksArab = intent.getStringExtra("teksArab")
        val teksLatin = intent.getStringExtra("teksLatin")
        val teksIndonesia = intent.getStringExtra("teksIndonesia")

        teksArabTextView.text = teksArab
        teksLatinTextView.text = teksLatin
        teksIndonesiaTextView.text = teksIndonesia

        tajwidData = loadTajwidData()

        detectTajwidButton.setOnClickListener {
            teksArab?.let { text ->
                val highlightedText = highlightTajwid(text)
                teksArabTextView.text = highlightedText
            }
        }
    }

    private fun loadTajwidData(): JSONArray {
        val inputStream: InputStream = resources.openRawResource(R.raw.tajwid)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        return JSONArray(jsonString)
    }

    private fun highlightTajwid(text: String): SpannableStringBuilder {
        val spannableStringBuilder = SpannableStringBuilder(text)
        val colors = listOf(
            Color.BLUE,    // Idgham Bighunnah
            Color.CYAN,    // Idgham Bilaghunnah
            Color.MAGENTA, // Iqlab
            Color.RED,     // Ikhfa
            Color.GREEN    // Izhar
        )

        // HashSet untuk menyimpan nama bacaan Tajwid yang sudah ditemukan
        val foundTajwidNames = HashSet<String>()

        for (i in 0 until tajwidData.length()) {
            val bacaan = tajwidData.getJSONObject(i)
            val namaBacaan = bacaan.getString("nama_bacaan")
            val hurufArray = bacaan.getJSONArray("huruf")
            val hurufList = mutableListOf<String>()

            for (j in 0 until hurufArray.length()) {
                hurufList.add(hurufArray.getString(j))
            }

            val regexPattern = buildRegexPattern(hurufList)
            val regex = Regex(regexPattern)
            val matches = regex.findAll(text)
            var offset = 0

            for (match in matches) {
                val tajwidName = " $namaBacaan "
                val start = match.range.first + offset
                val end = match.range.last + 1 + offset

                spannableStringBuilder.setSpan(
                    ForegroundColorSpan(colors[i]),
                    start,
                    end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                spannableStringBuilder.insert(end, tajwidName)

                spannableStringBuilder.setSpan(
                    SubscriptSpan(),
                    end,
                    end + tajwidName.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                spannableStringBuilder.setSpan(
                    RelativeSizeSpan(0.5f),
                    end,
                    end + tajwidName.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                offset += tajwidName.length

                // Tambahkan nama bacaan Tajwid ke HashSet jika belum ada
                foundTajwidNames.add(namaBacaan)
            }
        }

        // Hapus semua bacaan Tajwid yang tidak ditemukan
        val startIndex = spannableStringBuilder.length
        for (name in foundTajwidNames) {
            spannableStringBuilder.insert(startIndex, name)
        }

        return spannableStringBuilder
    }

    private fun buildRegexPattern(hurufList: List<String>): String {
        // Menyesuaikan pola regex untuk mencocokkan tanwin tanpa garis
        return "((ً|ٍ|ٌ)(?!ـ))|نْ|ن(ً|ٍ|ٌ)"
    }
}
