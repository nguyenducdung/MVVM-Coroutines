package com.dungnd.mvvm.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dungnd.mvvm.data.local.dao.UserDao
import com.dungnd.mvvm.data.local.model.User
import com.dungnd.mvvm.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val userDao: UserDao
) : BaseViewModel() {
    var user = MutableLiveData<User?>(null)

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            user.postValue(userDao.getAllUser()?.get(0))
        }
    }
}