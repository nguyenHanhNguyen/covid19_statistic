package com.n2n.covid19.ui.main.summary

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.n2n.covid19.R
import com.n2n.covid19.core.BaseFragment
import com.n2n.covid19.databinding.MainFragmentBinding
import com.n2n.covid19.model.country.local.CountryDbEntity
import com.n2n.covid19.model.summary.GlobalView
import com.n2n.covid19.model.summary.SummaryCountryView
import com.n2n.covid19.ui.main.filter.FilterBottomSheetDialog
import com.n2n.covid19.ui.main.filter.SortFragment
import com.n2n.covid19.ui.main.filter.search.SearchFragment

class MainFragment : BaseFragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    val filterDialog: FilterBottomSheetDialog by lazy { FilterBottomSheetDialog.newInstance() }

    private val viewModel by viewModels<MainViewModel> { viewModelFactory }

    private lateinit var binding: MainFragmentBinding
    private lateinit var countryAdapter: CountryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        appComponent.inject(this)
        binding.apply {
            viewModelBinding = viewModel
        }
        binding.mainHeader.apply {
            globalBinding = viewModel
        }
        binding.fabSort.setOnClickListener {
            filterDialog.show(requireActivity().supportFragmentManager, "FILTER_DIALOG")
        }

        setUpCountryList()
        setUpFilter()
    }

    private fun setUpCountryList() {
        viewModel.listSummary.observe(
            viewLifecycleOwner, Observer {
                renderCountryList(it)
            }
        )
        viewModel.global.observe(
            viewLifecycleOwner, Observer {
                renderGlobal(it)
            }
        )
    }

    private fun setUpFilter() {
        filterDialog.onSortClick = object : SortFragment.OnSortClick {
            override fun onDeathAscClick() {
                viewModel.sortByDeathAscending()
                filterDialog.dismiss()
            }

            override fun onDeathDescClick() {
                viewModel.sortByDeathDescending()
                filterDialog.dismiss()
            }

            override fun onConfirmAscClick() {
                viewModel.sortByTotalConfirmedAscending()
                filterDialog.dismiss()
            }

            override fun onConfirmDescClick() {
                viewModel.sortByTotalConfirmedDescending()
                filterDialog.dismiss()
            }
        }
//        filterDialog.onSearchResultClick = object : SearchFragment.OnSearchResultClick {
//            override fun setResult(country: CountryDbEntity) {
//                countryAdapter.filter.filter(country.country)
//                filterDialog.dismiss()
//            }
//        }
    }

    private fun renderCountryList(listSummary: List<SummaryCountryView>) {
        countryAdapter = CountryAdapter(listSummary)
        countryAdapter.onBookMarkClick = object : CountryAdapter.OnBookMarkClick {
            override fun ClickBookmark(country: SummaryCountryView) {
                Log.e("bookmark_country", country.country)
            }
        }
        binding.rvCountries.layoutManager = LinearLayoutManager(context)
        binding.rvCountries.adapter = countryAdapter
        binding.tvUpdated.text = getString(R.string.tv_date, listSummary[0].date)
    }

    private fun renderGlobal(globalView: GlobalView) {
        binding.mainHeader.viewPercent.percentDeath = globalView.totalDeathRaw.toFloat() / globalView.totalConfirmedRaw
        binding.mainHeader.viewPercent.percentRecover = globalView.totalRecoveredRaw.toFloat() / globalView.totalConfirmedRaw
        binding.mainHeader.viewPercent.invalidate()
    }

}