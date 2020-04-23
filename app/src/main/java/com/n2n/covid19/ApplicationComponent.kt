package com.n2n.covid19

import com.n2n.covid19.core.ViewModelModule
import com.n2n.covid19.ui.main.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(mainFragment: MainFragment)
}