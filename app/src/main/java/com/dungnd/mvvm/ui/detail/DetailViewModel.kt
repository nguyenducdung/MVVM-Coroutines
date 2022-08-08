package com.dungnd.mvvm.ui.detail

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dungnd.mvvm.data.local.LocalRepository
import com.dungnd.mvvm.data.local.model.User
import com.dungnd.mvvm.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class DetailViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : BaseViewModel() {
    var user = MutableLiveData<User>()

    init {
        viewModelScope.launch {
            user.value = localRepository.getUser()
        }
        viewModelScope.launch(Dispatchers.IO) {

        }
    }
}