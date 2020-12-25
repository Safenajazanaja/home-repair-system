package com.example.loginmvvm.ui.login

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
            request.username.length < 4 -> _toast.value = "Username <4"
            request.password.length < 4 -> _toast.value = "Password <4"
            else -> {
                val result = DataSource.login(request)

                _login.value = result
            }

        }

    }


}