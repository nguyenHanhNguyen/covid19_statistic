package com.n2n.covid19.ui.main.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.n2n.covid19.R
import kotlinx.android.synthetic.main.filter_bottom_sheet_dialog.*

class FilterBottomSheetDialog : BottomSheetDialogFragment() {

    interface OnSortClick {
        fun onDeathAscClick()
        fun onDeathDescClick()
        fun onConfirmAscClick()
        fun onConfirmDescClick()
        fun onRecoverAscClick()
        fun onRecoverDescClick()
    }

    var onSortClick: OnSortClick? = null

    companion object {
        fun newInstance() = FilterBottomSheetDialog()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.filter_bottom_sheet_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpClick()
        setUpViewPager()
    }

    private fun setUpViewPager() {
        val pagerAdapter = ViewPagerAdapter(this)
        view_pager.adapter = pagerAdapter
        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            when (position) {
                0 -> tab.text = "Sort"
                else -> tab.text = "Search"
            }
        }.attach()
    }

    private fun setUpClick() {

    }
}