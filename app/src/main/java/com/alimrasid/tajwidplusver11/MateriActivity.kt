package com.alimrasid.tajwidplusver11

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
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

        val toolbar: Toolbar = findViewById(R.id.toolbarMateri)
        setSupportActionBar(toolbar)

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

        supportActionBar?.title = ""

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }
}