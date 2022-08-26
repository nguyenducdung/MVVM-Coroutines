package com.dungnd.mvvm.ui.home

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dungnd.mvvm.BaseApp
import com.dungnd.mvvm.data.ContactRepository
import com.dungnd.mvvm.data.local.AppPreferences
import com.dungnd.mvvm.data.remote.RemoteRepository
import com.dungnd.mvvm.data.remote.model.Photo
import com.dungnd.mvvm.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val appPreferences: AppPreferences,
    private val remoteRepository: RemoteRepository,
    private val contactRepository: ContactRepository
) : BaseViewModel() {
    var name = MutableLiveData<String>()
    var photoList = MutableLiveData<List<Photo>>()
    var phoneList = MutableLiveData<List<String>>()

    init {
        name.value = appPreferences.getName()
        appPreferences.saveName("Hihi, tôi là Dũng")
        getAllPhoto()
    }

    private fun getAllPhoto() {
        viewModelScope.launch {
            photoList.value = remoteRepository.getAllPhotos()
        }

        viewModelScope.launch(Dispatchers.IO) {
            val data = contactRepository.getPhone()
            phoneList.postValue(data)
        }
    }
}