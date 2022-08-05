package com.dungnd.mvvm.ui.edit

import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.dungnd.mvvm.R
import com.dungnd.mvvm.databinding.FragmentEditProfileBinding
import com.dungnd.mvvm.ui.base.BaseFragment
import com.dungnd.mvvm.util.setOnSingClickListener

class EditProfileFragment : BaseFragment<FragmentEditProfileBinding, EditProfileViewModel>() {
    private var newPhone = ""
    override fun layoutRes(): Int {
        return R.layout.fragment_edit_profile
    }

    override fun viewModelClass(): Class<EditProfileViewModel> = EditProfileViewModel::class.java

    //Chỉ gọi 1 lần
    override fun initView() {
        binding.btnSave.setOnSingClickListener {
            val fullName = binding.edtFullName.text.toString()
            val address = binding.edtAddress.text.toString()
            val phone = binding.edtPhone.text.toString()
            viewModel.saveData(fullName, address, phone)
            //Set dữ liệu cho biến phone trong mainView model để dùng
            //chung

            findNavController().navigate(R.id.action_editProfileFragment_to_detailFragment)
        }

        //Lắng nghe sự thay đổi của biến phone
        mainViewModel.phone.observe(this) {
            newPhone = it
        }
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(requireContext(), newPhone, Toast.LENGTH_SHORT).show()
    }

}