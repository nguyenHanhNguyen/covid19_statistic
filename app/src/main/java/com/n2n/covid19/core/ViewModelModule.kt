package com.n2n.covid19.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.n2n.covid19.ui.main.filter.search.SearchViewModel
import com.n2n.covid19.ui.main.summary.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Singleton
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun provideViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun provideSearchViewModel(searchViewModel: SearchViewModel): ViewModel
}