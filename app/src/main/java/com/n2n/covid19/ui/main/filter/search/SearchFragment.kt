package com.n2n.covid19.ui.main.filter.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.n2n.covid19.R
import com.n2n.covid19.core.BaseFragment
import com.n2n.covid19.model.country.local.CountryDbEntity
import com.n2n.covid19.ui.main.widget.CustomTextWatcher
import kotlinx.android.synthetic.main.search_fragment.*

class SearchFragment : BaseFragment() {

    private val searchViewModel by viewModels<SearchViewModel> { viewModelFactory }
    var adapter = SearchCountryAdapter()

    interface OnSearchResultClick {
        fun setSlug(slug: String)
    }

    var onSearchResultClick: OnSearchResultClick? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        appComponent.inject(this)
        searchViewModel.countries.observe(
            viewLifecycleOwner, Observer {
                renderResultCountry(it)
            }
        )
        adapter.onItemClick = object : SearchCountryAdapter.OnItemClick {
            override fun onCountryClick(slug: String) {
                onSearchResultClick?.setSlug(slug)
            }
        }
        rv_country_result.layoutManager = LinearLayoutManager(context)
        rv_country_result.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ed_search_country.addTextChangedListener(object : CustomTextWatcher() {
            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.spanText = text.toString()
                if (text.toString().isNotEmpty()) {
                    searchViewModel.searchCountry(text.toString())
                } else {
                    adapter.countries.clear()
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }

    private fun renderResultCountry(countries: List<CountryDbEntity>) {
        adapter.countries.clear()
        adapter.countries.addAll(countries)
        adapter.notifyDataSetChanged()
    }
}