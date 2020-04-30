package com.n2n.covid19.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.n2n.covid19.core.BaseFragment
import com.n2n.covid19.core.ViewModelFactory
import com.n2n.covid19.databinding.MainFragmentBinding
import com.n2n.covid19.model.summary.GlobalView
import com.n2n.covid19.model.summary.SummaryView
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

class MainFragment : BaseFragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<MainViewModel> { viewModelFactory }

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        appComponent.inject(this)
        setUpCountryList()
    }

    private fun setUpCountryList() {
        viewModel.loading.observe(
            viewLifecycleOwner, Observer {
                if (it) {
                    binding.progressLoading.visibility = VISIBLE
                } else {
                    binding.progressLoading.visibility = GONE
                }
            }
        )
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

    private fun renderCountryList(listSummary: List<SummaryView>) {
        val countryAdapter = CountryAdapter(listSummary)
        binding.rvCountries.layoutManager = LinearLayoutManager(context)
        binding.rvCountries.adapter = countryAdapter
    }

    private fun renderGlobal(globalView: GlobalView) {
        binding.tvNewConfirmed.text = globalView.newConfirmed
        binding.tvTotalConfirmed.text = globalView.totalConfirmed
        binding.tvNewDeath.text = globalView.newDeath
        binding.tvTotalDeath.text = globalView.totalDeath
        binding.tvNewRecovered.text = globalView.newRecovered
        binding.tvTotalRecovered.text = globalView.totalRecovered
    }


}