package com.alimrasid.tajwidplusver11.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alimrasid.tajwidplusver11.fragment.FariFragment
import com.alimrasid.tajwidplusver11.fragment.IkhfaHakikiFragment
import com.alimrasid.tajwidplusver11.fragment.IkhfaSyafawiFragment
import com.alimrasid.tajwidplusver11.fragment.KubraFragment
import com.alimrasid.tajwidplusver11.fragment.SugraFragment
import com.alimrasid.tajwidplusver11.fragment.ThabiiFragment

class LainPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {



    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    private val fragments = listOf(
        ThabiiFragment(),
        FariFragment(),
        SugraFragment(),
        KubraFragment()
    )
}