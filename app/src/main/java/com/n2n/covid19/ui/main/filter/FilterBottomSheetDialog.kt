package com.n2n.covid19.ui.main.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.n2n.covid19.R
import kotlinx.android.synthetic.main.filter_bottom_sheet_dialog.*

class FilterBottomSheetDialog : BottomSheetDialogFragment() {

    var onSortClick: SortFragment.OnSortClick? = null

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogStyle)
    }

    private fun setUpViewPager() {
        val pagerAdapter = ViewPagerAdapter(this)
        pagerAdapter.onSortClick = onSortClick
        view_pager.adapter = pagerAdapter
        view_pager.isSaveEnabled = false //prevent crash
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