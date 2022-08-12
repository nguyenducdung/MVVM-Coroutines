package com.dungnd.mvvm.ui.profile

import android.net.Uri
import com.bumptech.glide.Glide
import com.dungnd.mvvm.R
import com.dungnd.mvvm.databinding.FragmentProfileBinding
import com.dungnd.mvvm.ui.base.BaseFragment

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {
    override fun layoutRes(): Int  = R.layout.fragment_profile

    override fun viewModelClass(): Class<ProfileViewModel> = ProfileViewModel::class.java

    override fun initView() {
        viewModel.user.observe(this) {
            if (it != null) {
                binding.tvName.text = "${it.name} ${it.email}"
                Glide.with(requireContext()).load(it.image).into(binding.ivAvatar)
            }
        }
    }

}