package com.alimrasid.tajwidplusver11.materi

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alimrasid.tajwidplusver11.R

class IdghamListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_idgham_list)

        setupCardView(
            findViewById(R.id.cardViewIdgghom),
            findViewById(R.id.toggleButton),
            findViewById(R.id.descriptionTextView1)
        )

        setupCardView(
            findViewById(R.id.cardViewIdgghom2),
            findViewById(R.id.toggleButton2),
            findViewById(R.id.descriptionTextView2)
        )

        setupCardView(
            findViewById(R.id.cardViewIdgghom3),
            findViewById(R.id.toggleButton3),
            findViewById(R.id.descriptionTextView3)
        )

        setupCardView(
            findViewById(R.id.cardViewIdgghom4),
            findViewById(R.id.toggleButton4),
            findViewById(R.id.descriptionTextView4)
        )

        setupCardView(
            findViewById(R.id.cardViewIdgghom5),
            findViewById(R.id.toggleButton5),
            findViewById(R.id.descriptionTextView5)
        )

        setupCardView(
            findViewById(R.id.cardViewIdgghom6),
            findViewById(R.id.toggleButton6),
            findViewById(R.id.descriptionTextView6)
        )
    }

    private fun setupCardView(cardView: CardView, toggleButton: ImageButton, descriptionTextView: TextView) {
        cardView.setOnClickListener {
            if (descriptionTextView.visibility == View.GONE) {
                // Expand
                descriptionTextView.visibility = View.VISIBLE
                toggleButton.setImageResource(android.R.drawable.arrow_up_float)
                animateView(descriptionTextView, true)
            } else {
                // Collapse
                descriptionTextView.visibility = View.GONE
                toggleButton.setImageResource(android.R.drawable.arrow_down_float)
                animateView(descriptionTextView, false)
            }
        }
    }

    private fun animateView(view: View, expanding: Boolean) {
        val animation = if (expanding) AlphaAnimation(0.0f, 1.0f) else AlphaAnimation(1.0f, 0.0f)
        animation.duration = 300
        animation.fillAfter = true
        view.startAnimation(animation)
    }
}