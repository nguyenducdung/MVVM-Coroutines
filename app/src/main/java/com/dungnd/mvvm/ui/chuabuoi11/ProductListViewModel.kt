package com.dungnd.mvvm.ui.chuabuoi11

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dungnd.mvvm.data.remote.model.ProductResponse
import com.dungnd.mvvm.data.remote.RemoteRepository
import com.dungnd.mvvm.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductListViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
) : BaseViewModel() {

    var productResponse = MutableLiveData<ProductResponse>(null)
    var isLoading = MutableLiveData<Boolean>(false)

    init {
        //hàm khởi tạo của 1 class
        getAllProductV2()
    }

    private fun getAllProductV2() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)
            val data = remoteRepository.getAllProductV2()
            productResponse.postValue(data)
            isLoading.postValue(false)
        }
    }
}