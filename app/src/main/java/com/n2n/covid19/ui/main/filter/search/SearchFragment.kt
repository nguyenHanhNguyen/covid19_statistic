package com.n2n.covid19.ui.main.filter.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.n2n.covid19.R
import com.n2n.covid19.core.BaseFragment
import com.n2n.covid19.ui.main.widget.CustomTextWatcher
import kotlinx.android.synthetic.main.search_fragment.*

class SearchFragment : BaseFragment() {

    private val searchViewModel by viewModels<SearchViewModel> { viewModelFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ed_search_country.addTextChangedListener(object: CustomTextWatcher() {
            override fun onTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
                searchViewModel.searchCountry(text.toString())
            }
        })
    }
}