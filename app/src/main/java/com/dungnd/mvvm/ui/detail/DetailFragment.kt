package com.dungnd.mvvm.ui.detail

import com.dungnd.mvvm.R
import com.dungnd.mvvm.databinding.FragmentDetailBinding
import com.dungnd.mvvm.ui.base.BaseFragment

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {
    override fun layoutRes(): Int = R.layout.fragment_detail

    override fun viewModelClass(): Class<DetailViewModel> = DetailViewModel::class.java

    override fun initView() {
//        val title = arguments?.getString("key") ?:""
//        binding.tvDetail.text = title

        viewModel.user.observe(this) {
            if (it != null) {
                binding.tvDetail.text = "${it.name}_${it.address}"
            }
        }
        mainViewModel.phone.postValue("fsafjaskfjaksdf")
    }
}