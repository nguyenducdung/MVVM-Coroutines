package com.dungnd.mvvm.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dungnd.mvvm.di.viewmodel.ViewModelFactory
import com.dungnd.mvvm.di.viewmodel.ViewModelKey
import com.dungnd.mvvm.ui.chuabuoi11.ProductListViewModel
import com.dungnd.mvvm.ui.detail.DetailViewModel
import com.dungnd.mvvm.ui.home.HomeViewModel
import com.dungnd.mvvm.ui.login.LoginViewModel
import com.dungnd.mvvm.ui.main.MainViewModel
import com.dungnd.mvvm.ui.profile.ProfileViewModel
import com.dungnd.mvvm.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun mainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    internal abstract fun splashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun homeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    internal abstract fun detailViewModel(viewModel: DetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductListViewModel::class)
    internal abstract fun productListViewModel(viewModel: ProductListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun loginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    internal abstract fun profileViewModel(viewModel: ProfileViewModel): ViewModel

}