package com.n2n.covid19.ui.main.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
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
    }

    private fun setUpClick() {
        imv_death_ascending.setOnClickListener { onSortClick?.onDeathAscClick() }
        imv_death_descending.setOnClickListener { onSortClick?.onDeathDescClick() }
        imv_confirm_ascending.setOnClickListener { onSortClick?.onConfirmAscClick() }
        imv_confirm_descending.setOnClickListener { onSortClick?.onConfirmDescClick() }
        imv_recovered_ascending.setOnClickListener { onSortClick?.onRecoverAscClick() }
        imv_recovered_descending.setOnClickListener { onSortClick?.onRecoverDescClick() }
    }
}