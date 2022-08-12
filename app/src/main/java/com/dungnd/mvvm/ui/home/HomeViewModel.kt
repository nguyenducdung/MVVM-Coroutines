package com.dungnd.mvvm.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dungnd.mvvm.data.local.AppPreferences
import com.dungnd.mvvm.data.remote.RemoteRepository
import com.dungnd.mvvm.data.remote.model.Photo
import com.dungnd.mvvm.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val appPreferences: AppPreferences,
    private val remoteRepository: RemoteRepository
) : BaseViewModel() {
    var name = MutableLiveData<String>()
    var photoList = MutableLiveData<List<Photo>>()
    var isLoading = MutableLiveData(false)

    init {
        name.value = appPreferences.getName()
        appPreferences.saveName("Hihi, tôi là Dũng")
        getAllPhoto()
    }

    private fun getAllPhoto() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                photoList.value = remoteRepository.getAllPhotos()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            isLoading.value = false
        }
    }
}