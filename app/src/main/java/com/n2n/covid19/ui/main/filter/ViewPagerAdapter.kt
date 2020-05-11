package com.n2n.covid19.ui.main.filter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    var onSortClick: SortFragment.OnSortClick ?= null

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> SortFragment().apply {
                onClick = onSortClick
            }
            else -> SearchFragment()
        }
    }
    
}