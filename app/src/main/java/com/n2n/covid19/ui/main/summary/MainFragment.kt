package com.n2n.covid19.ui.main.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.n2n.covid19.R
import com.n2n.covid19.core.BaseFragment
import com.n2n.covid19.core.ViewModelFactory
import com.n2n.covid19.databinding.MainFragmentBinding
import com.n2n.covid19.model.summary.SummaryCountryView
import com.n2n.covid19.ui.main.filter.FilterBottomSheetDialog
import javax.inject.Inject

class MainFragment : BaseFragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val filterDialog: FilterBottomSheetDialog by lazy {FilterBottomSheetDialog.newInstance()}

    private val viewModel by viewModels<MainViewModel> { viewModelFactory }

    private lateinit var binding: MainFragmentBinding

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
        binding.fabSort.setOnClickListener{
            activity?.let {
                filterDialog.show(it.supportFragmentManager, "FILTER_DIALOG")
            }
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
    }

    private fun setUpFilter() {
        filterDialog.onSortClick = object: FilterBottomSheetDialog.OnSortClick {
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

            override fun onRecoverAscClick() {
                viewModel.sortByRecoveredAscending()
                filterDialog.dismiss()
            }

            override fun onRecoverDescClick() {
                viewModel.sortByRecoveredDescending()
                filterDialog.dismiss()
            }
        }
    }

    private fun renderCountryList(listSummary: List<SummaryCountryView>) {
        val countryAdapter = CountryAdapter(listSummary)
        binding.rvCountries.layoutManager = LinearLayoutManager(context)
        binding.rvCountries.adapter = countryAdapter
        binding.tvUpdated.text = getString(R.string.tv_date, listSummary[0].date)
    }

}