package com.dungnd.mvvm.ui.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dungnd.mvvm.R
import com.dungnd.mvvm.data.local.AppPreferences
import com.dungnd.mvvm.data.remote.RemoteRepository
import com.dungnd.mvvm.data.remote.model.AddressData
import com.dungnd.mvvm.data.remote.model.Photo
import com.dungnd.mvvm.data.remote.model.ProductData
import com.dungnd.mvvm.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val appPreferences: AppPreferences,
    private val remoteRepository: RemoteRepository
) : BaseViewModel() {
    var name = MutableLiveData<String>()
    var photoList = MutableLiveData<List<Photo>>()

    var productList = MutableLiveData<List<ProductData>>()
    var address = MutableLiveData<AddressData>()

    var isLoading = MutableLiveData<Boolean>(false)

    init {
        name.value = appPreferences.getName()
        appPreferences.saveName("Hihi, tôi là Dũng")
//        getAllPhoto()

        //Cách 1: Gọi trong hàm khởi tạo của viewmodel, chỉ đc 1 lần
        //Áp dụng với api k truyền tham số, vì lý do khác
        getAllProducts()
    }

    private fun getAllPhoto() {
        //Default
        viewModelScope.launch {
            photoList.value = remoteRepository.getAllPhotos()
        }
    }

    private fun getAllProducts() {
//        viewModelScope.launch(Dispatchers.IO) {
//            //luồng gọi api ở background
//            try {
//                //Dữ liệu nhận được khi gọi api thành công
//                val data = remoteRepository.getAllProducts()
//                //dùng postValue để đẩy dữ liệu lên UI thread và hiện dữ liệu cho người dùng
//                productList.postValue(data)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
        viewModelScope.launch(Dispatchers.IO) {
            //Lúc bắt đầu call api thì hiển thị loading
            isLoading.postValue(true)
            //đang gọi song song 2 api
            val data = async(Dispatchers.Main) { remoteRepository.getAllProducts() }
            val data2 = async { remoteRepository.getAllAddress() }
            productList.postValue(data.await())
            address.postValue(data2.await())

            //khi api đã gọi xong
            isLoading.postValue(false)
        }
    }

    private fun getAllAddress() {
        viewModelScope.launch(Dispatchers.IO) {
            //luồng gọi api ở background
            try {
                //Dữ liệu nhận được khi gọi api thành công
                val data = remoteRepository.getAllAddress()
                //dùng postValue để đẩy dữ liệu lên UI thread và hiện dữ liệu cho người dùng
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun createProduct() {
        //Gọi api tạo product mà k quan tâm kết quả trả về
        viewModelScope.launch(Dispatchers.IO) {
            //luồng gọi api ở background
            try {
                //Dữ liệu nhận được khi gọi api thành công
                remoteRepository.createProduct()
                //dùng postValue để đẩy dữ liệu lên UI thread và hiện dữ liệu cho người dùng
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}