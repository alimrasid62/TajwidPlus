package com.alimrasid.tajwidplusver11.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alimrasid.tajwidplusver11.fragment.HalqiFragment
import com.alimrasid.tajwidplusver11.fragment.IkhfaHakikiFragment
import com.alimrasid.tajwidplusver11.fragment.IkhfaSyafawiFragment
import com.alimrasid.tajwidplusver11.fragment.SyafawiFragment

class IkhfaViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf(
        IkhfaHakikiFragment(),
        IkhfaSyafawiFragment()
    )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}