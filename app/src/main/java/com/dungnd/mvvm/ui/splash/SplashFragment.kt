package com.dungnd.mvvm.ui.splash

import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import com.dungnd.mvvm.R
import com.dungnd.mvvm.databinding.FragmentSplashBinding
import com.dungnd.mvvm.ui.base.BaseFragment

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {
    override fun layoutRes(): Int = R.layout.fragment_splash

    override fun viewModelClass(): Class<SplashViewModel> = SplashViewModel::class.java

    override fun initView() {
//        Handler(Looper.getMainLooper()).postDelayed({
//            findNavController().navigate(R.id.action_splashFragment_to_editProfileFragment)
//        }, 2000)
        val a = 0
        val b = 1
        val c = a+ b
        Handler(Looper.getMainLooper()).postDelayed({
            //Thực hiện các lệnh sau khoảng thời gian là 3000 mili giây
            findNavController().navigate(R.id.action_splashFragment_to_editProfileFragment)
        }, 3000)
        val d= a +c
    }
}