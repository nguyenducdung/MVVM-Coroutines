package com.dungnd.mvvm.ui.home

import androidx.lifecycle.MutableLiveData
import com.dungnd.mvvm.data.local.AppPreferences
import com.dungnd.mvvm.ui.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val appPreferences: AppPreferences
) : BaseViewModel() {
    var name = MutableLiveData<String>()

    init {
        name.value = appPreferences.getName()
        appPreferences.saveName("Hihi, tôi là Dũng")
    }
}