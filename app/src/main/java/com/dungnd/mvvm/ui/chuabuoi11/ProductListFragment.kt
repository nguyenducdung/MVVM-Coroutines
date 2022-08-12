package com.dungnd.mvvm.ui.chuabuoi11

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dungnd.mvvm.R
import com.dungnd.mvvm.databinding.FragmentProductListBinding
import com.dungnd.mvvm.ui.base.BaseActivity
import com.dungnd.mvvm.ui.base.BaseFragment
import com.dungnd.mvvm.ui.main.MainActivity
import com.dungnd.mvvm.ui.main.MainViewModel

class ProductListFragment : BaseFragment<FragmentProductListBinding, ProductListViewModel>() {
    override fun layoutRes(): Int = R.layout.fragment_product_list

    override fun viewModelClass(): Class<ProductListViewModel> = ProductListViewModel::class.java

    override fun initView() {
        viewModel.isLoading.observe(this) {
            if (it == true) {
                (activity as? MainActivity)?.showLoading()
            } else {
                (activity as? MainActivity)?.hiddenLoading()
            }
        }

        viewModel.productResponse.observe(this) {
            if (it == null) {
                return@observe
            }
            val adapter = PhotoListAdapter()
            adapter.photoList = it.data ?: ArrayList()
            adapter.onPhotoClick = {

            }
            binding.rcvProduct.adapter = adapter
        }
    }

}