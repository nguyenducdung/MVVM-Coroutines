package com.dungnd.mvvm.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dungnd.mvvm.data.local.AppPreferences
import com.dungnd.mvvm.data.local.dao.UserDao
import com.dungnd.mvvm.data.local.model.User
import com.dungnd.mvvm.ui.base.BaseViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val userDao: UserDao,
    private val appPreferences: AppPreferences
) : BaseViewModel() {
    var loginSuccess = MutableLiveData<Boolean>(false)
    var isAddUserSuccess = MutableLiveData<Boolean>(false)

    fun addUser(account: GoogleSignInAccount) {
        val email = account.email
        val image = account.photoUrl
        val name = account.displayName
        val id = account.id
        //đây là token mà chúng ta cần để gửi lên Backend
        val token = account.idToken
        appPreferences.saveToken(token ?:"")
        val user = User(
            id = id ?:"",
            email = email ?:"",
            name = name ?:"",
            image = image.toString(),
            passWord = "123456"
        )
        viewModelScope.launch(Dispatchers.IO) {
            userDao.addUser(user)
            isAddUserSuccess.postValue(true)
        }
    }

    fun login(email: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userDao.getUser(email)
            if (user != null && pass == user.passWord) {
                loginSuccess.postValue(true)
            }
        }
    }
}