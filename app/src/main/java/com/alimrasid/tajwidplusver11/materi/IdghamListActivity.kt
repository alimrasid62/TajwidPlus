package com.alimrasid.tajwidplusver11.materi

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alimrasid.tajwidplusver11.R

class IdghamListActivity : AppCompatActivity() {

    private lateinit var cardView: CardView
    private lateinit var descriptionTextView: TextView
    private lateinit var toggleButton: ImageButton
    private var isExpanded: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_idgham_list)

        cardView = findViewById(R.id.cardViewIdgghom)
        descriptionTextView = findViewById(R.id.descriptionTextView)
        toggleButton = findViewById(R.id.toggleButton)

        cardView.setOnClickListener {
            toggleCardView()
        }
    }

    private fun toggleCardView() {
        if (isExpanded) {
            collapse(descriptionTextView)
            toggleButton.setImageResource(android.R.drawable.arrow_down_float)
        } else {
            expand(descriptionTextView)
            toggleButton.setImageResource(android.R.drawable.arrow_up_float)
        }
        isExpanded = !isExpanded
    }

    private fun expand(view: View) {
        view.measure(View.MeasureSpec.makeMeasureSpec(view.width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
//        view.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val targetHeight = view.measuredHeight

        view.layoutParams.height = 0
        view.visibility = View.VISIBLE

        val animator = ValueAnimator.ofInt(0, targetHeight)
        animator.addUpdateListener { animation ->
            val value = animation.animatedValue as Int
            view.layoutParams.height = value
            view.requestLayout()
        }
        animator.duration = 300
        animator.start()
    }

    private fun collapse(view: View) {
        val initialHeight = view.measuredHeight

        val animator = ValueAnimator.ofInt(initialHeight, 0)
        animator.addUpdateListener { animation ->
            val value = animation.animatedValue as Int
            view.layoutParams.height = value
            view.requestLayout()
            if (value == 0) {
                view.visibility = View.GONE
            }
        }
        animator.duration = 300
        animator.start()
    }
}