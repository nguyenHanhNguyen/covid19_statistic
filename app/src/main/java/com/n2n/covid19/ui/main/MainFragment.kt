package com.n2n.covid19.ui.main

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
import com.n2n.covid19.model.CountryView
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

class MainFragment : BaseFragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<MainViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        appComponent.inject(this)
        viewModel.getCountrySummary()
        setUpCountryList()
    }

    private fun setUpCountryList() {
        viewModel.listCountry.observe(
            this, Observer {
                renderCountryList(it)
            }
        )
    }

    private fun renderCountryList(listCountry: List<CountryView>) {
        val countryAdapter = CountryAdapter(listCountry)
        rv_countries.layoutManager = LinearLayoutManager(context)
        rv_countries.adapter = countryAdapter
    }



}