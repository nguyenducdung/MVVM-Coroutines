package com.dungnd.mvvm.ui.home

import com.dungnd.mvvm.R
import com.dungnd.mvvm.databinding.FragmentHomeBinding
import com.dungnd.mvvm.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override fun layoutRes(): Int = R.layout.fragment_home

    override fun viewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun initView() {
        viewModel.name.observe(this) {
            binding.tvName.text = it
        }
    }

}