package com.alimrasid.tajwidplusver11

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alimrasid.tajwidplusver11.materi.IdghamListActivity
import com.alimrasid.tajwidplusver11.materi.IkhfaListActivity
import com.alimrasid.tajwidplusver11.materi.IqlabActivity
import com.alimrasid.tajwidplusver11.materi.IzharListActivity
import com.alimrasid.tajwidplusver11.materi.ListLainActivity

class MateriActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materi)

        val cardView1: CardView = findViewById(R.id.cardIdhar)
        val cardView2: CardView = findViewById(R.id.cardIdghom)
        val cardView3: CardView = findViewById(R.id.cardIkhfa)
        val cardView4: CardView = findViewById(R.id.cardIqlab)
        val cardView5: CardView = findViewById(R.id.cardLain)

        cardView1.setOnClickListener {
            val intent = Intent(this, IzharListActivity::class.java)
            startActivity(intent)
        }
        cardView2.setOnClickListener {
            val intent = Intent(this, IdghamListActivity::class.java)
            startActivity(intent)
        }
        cardView3.setOnClickListener {
            val intent = Intent(this, IkhfaListActivity::class.java)
            startActivity(intent)
        }
        cardView4.setOnClickListener {
            val intent = Intent(this, IqlabActivity::class.java)
            startActivity(intent)
        }
        cardView5.setOnClickListener {
            val intent = Intent(this, ListLainActivity::class.java)
            startActivity(intent)
        }

    }
}