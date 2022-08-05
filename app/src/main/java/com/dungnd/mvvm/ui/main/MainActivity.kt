package com.dungnd.mvvm.ui.main

import com.dungnd.mvvm.R
import com.dungnd.mvvm.databinding.ActivityMainBinding
import com.dungnd.mvvm.ui.base.BaseActivity
import com.dungnd.mvvm.util.showSoftKeyboard

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun layoutRes(): Int = R.layout.activity_main

    override fun viewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun initView() {

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }
}