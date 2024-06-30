package com.alimrasid.tajwidplusver11

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alimrasid.tajwidplusver11.activity.AboutActivity
import com.alimrasid.tajwidplusver11.activity.DoaActivity
import com.alimrasid.tajwidplusver11.activity.QuranActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cardView1: CardView = findViewById(R.id.card1)
        val cardView2: CardView = findViewById(R.id.card2)
        val cardView3: CardView = findViewById(R.id.card3)
        val cardView4: CardView = findViewById(R.id.card4)

        cardView1.setOnClickListener {
            val intent = Intent(this, MateriActivity::class.java)
            startActivity(intent)
        }

        cardView2.setOnClickListener {
            val intent = Intent(this, QuranActivity::class.java)
            startActivity(intent)
        }

        cardView3.setOnClickListener {
            val intent = Intent(this, DoaActivity::class.java)
            startActivity(intent)
        }

        cardView4.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        val inflater = LayoutInflater.from(this)
        val dialogView = inflater.inflate(R.layout.custom_dialog, null)

        val alertDialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        val dialogTitle = dialogView.findViewById<TextView>(R.id.dialog_title)
        val dialogMessage = dialogView.findViewById<TextView>(R.id.dialog_message)
        val buttonNo = dialogView.findViewById<Button>(R.id.button_no)
        val buttonYes = dialogView.findViewById<Button>(R.id.button_yes)

        buttonNo.setOnClickListener {
            alertDialog.dismiss()
        }

        buttonYes.setOnClickListener {
            super.onBackPressed()
        }

        alertDialog.show()
    }
}