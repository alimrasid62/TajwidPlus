package com.alimrasid.tajwidplusver11.materi

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.alimrasid.tajwidplusver11.R
import com.alimrasid.tajwidplusver11.adapter.IdghomViewPagerAdapter
import com.alimrasid.tajwidplusver11.adapter.IkhfaViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class IdghomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_idghom)
        val viewPager: ViewPager2 = findViewById(R.id.view_pagerIdhghom)
        val tabLayout: TabLayout = findViewById(R.id.tab_layoutIdhghom)

        viewPager.adapter = IdghomViewPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Idghom Bigunnah"
                1 -> tab.text = " Idghom Bilagunnah"
            }
        }.attach()

        val toolbar: Toolbar = findViewById(R.id.toolbarIdghom)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Pengertian Idghom"

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