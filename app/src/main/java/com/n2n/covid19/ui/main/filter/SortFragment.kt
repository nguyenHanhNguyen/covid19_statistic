package com.n2n.covid19.ui.main.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.n2n.covid19.R
import kotlinx.android.synthetic.main.sort_fragment.*

class SortFragment : Fragment() {

    interface OnSortClick {
        fun onDeathAscClick()
        fun onDeathDescClick()
        fun onConfirmAscClick()
        fun onConfirmDescClick()
    }

    var onClick: OnSortClick? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.sort_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imv_death_ascending.setOnClickListener { onClick?.onDeathAscClick() }
        imv_death_descending.setOnClickListener { onClick?.onDeathDescClick() }
        imv_confirm_ascending.setOnClickListener { onClick?.onConfirmAscClick() }
        imv_confirm_descending.setOnClickListener { onClick?.onConfirmDescClick() }
    }
}