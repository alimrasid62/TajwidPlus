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
import com.alimrasid.tajwidplusver11.adapter.IkhfaViewPagerAdapter
import com.alimrasid.tajwidplusver11.adapter.LainPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ListLainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_lain)
        val viewPager: ViewPager2 = findViewById(R.id.view_pagerLain)
        val tabLayout: TabLayout = findViewById(R.id.tab_layoutLain)

        viewPager.adapter = LainPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Mad Thabi'i"
                1 -> tab.text = "Mad Far'i"
                2 -> tab.text = "Qalqalah Sugra"
                3 -> tab.text = " Qalqalah Kubra"
            }
        }.attach()

        val toolbar: Toolbar = findViewById(R.id.toolbarLain)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Beberapa Takjwid Lainnya "

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