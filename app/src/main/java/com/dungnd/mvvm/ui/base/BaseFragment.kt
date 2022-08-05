package com.dungnd.mvvm.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.dungnd.mvvm.ui.main.MainViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

//Những gì dùng chung trong fragment
abstract class BaseFragment<T : ViewDataBinding, M : BaseViewModel> : DaggerFragment() {
    protected lateinit var binding: T
    protected lateinit var viewModel: M
    protected lateinit var mainViewModel: MainViewModel

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    @LayoutRes
    protected abstract fun layoutRes(): Int
    protected abstract fun viewModelClass(): Class<M>
    protected abstract fun initView()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel = ViewModelProvider(this, viewModelFactory)[viewModelClass()]
        mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        initView()
    }

    fun showLoading() {
        (activity as? BaseActivity<*, *>)?.showLoading()
    }

    fun hiddenLoading() {
        (activity as? BaseActivity<*, *>)?.hiddenLoading()
    }
}