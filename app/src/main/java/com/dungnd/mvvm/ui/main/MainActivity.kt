package com.dungnd.mvvm.ui.main

import android.content.Intent
import com.dungnd.mvvm.R
import com.dungnd.mvvm.databinding.ActivityMainBinding
import com.dungnd.mvvm.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun layoutRes(): Int = R.layout.activity_main

    override fun viewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun initView() {

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }
}