package com.alimrasid.tajwidplusver11.materi

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.alimrasid.tajwidplusver11.R
import com.alimrasid.tajwidplusver11.adapter.IkhfaViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class IkhfaListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ikhfa_list)

        val viewPager: ViewPager2 = findViewById(R.id.view_pagerikhfa)
        val tabLayout: TabLayout = findViewById(R.id.tab_layoutikfa)

        viewPager.adapter = IkhfaViewPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Ikhfa Syafawi"
                1 -> tab.text = " Ikhfa Syafawi"
            }
        }.attach()

        val toolbar: Toolbar = findViewById(R.id.toolbarikhfa)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Macam Ikhfa "

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