package com.n2n.covid19.core

import androidx.fragment.app.Fragment
import com.n2n.covid19.AndroidApplication
import com.n2n.covid19.ApplicationComponent
import javax.inject.Inject

open class BaseFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val appComponent: ApplicationComponent by lazy (mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as AndroidApplication).appComponent
    }

}