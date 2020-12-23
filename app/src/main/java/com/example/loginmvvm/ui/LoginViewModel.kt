package com.example.loginmvvm.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginmvvm.data.datasource.DataSource
import com.example.loginmvvm.data.models.LoginRequest

class LoginViewModel : ViewModel() {

    private var _toast = MutableLiveData<String>()
    val toast: LiveData<String>
        get() = _toast

    private var _login = MutableLiveData<Boolean>()
    val login: LiveData<Boolean>
        get() = _login

    fun login(request: LoginRequest) {
        when {
            request.username.isBlank() -> _toast.value = "Username blank"
            request.password.isBlank() -> _toast.value = "Password blank"
            else -> {
                val result = DataSource.login(request)

                _login.value = result
            }

        }

    }


}