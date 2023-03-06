package com.gramzin.gifless.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gramzin.gifless.presentation.fragments.ProgrammingGifFragment
import com.gramzin.gifless.presentation.fragments.TopGifFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> TopGifFragment()
            else -> ProgrammingGifFragment()
        }
    }
}