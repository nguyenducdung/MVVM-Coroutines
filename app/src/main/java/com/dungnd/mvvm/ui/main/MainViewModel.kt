package com.dungnd.mvvm.ui.main

import androidx.lifecycle.MutableLiveData
import com.dungnd.mvvm.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel() {
    var phone = MutableLiveData<String>()
}