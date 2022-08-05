package com.dungnd.mvvm.ui.edit

import androidx.lifecycle.viewModelScope
import com.dungnd.mvvm.data.local.AppPreferences
import com.dungnd.mvvm.data.local.LocalRepository
import com.dungnd.mvvm.data.local.model.User
import com.dungnd.mvvm.data.remote.RemoteRepository
import com.dungnd.mvvm.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditProfileViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository,
    private val appPreferences: AppPreferences
) : BaseViewModel() {

    //Đây là được gọi sau khi constructor đc khởi tạo thành công (gọi 1 lần duy nhất)
    init {

    }

    fun saveData(name: String, address: String, phone: String) {
        appPreferences.saveName(name)
        viewModelScope.launch {
            val user = User(name, address, phone)
            localRepository.saveUser(user)
        }
    }
}