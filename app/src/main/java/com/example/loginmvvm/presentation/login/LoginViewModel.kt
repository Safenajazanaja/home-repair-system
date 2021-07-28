package com.example.loginmvvm.presentation.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginmvvm.data.datasource.DataSource
import com.example.loginmvvm.data.request.LoginRequest
import com.example.loginmvvm.data.response.LoginResponse

class LoginViewModel : ViewModel() {

    private var _toast = MutableLiveData<String>()
    val toast: LiveData<String>
        get() = _toast

    private var _login = MutableLiveData<Boolean>()
    val login: LiveData<Boolean>
        get() = _login

    private var _id = MutableLiveData<Int>()
    val id: LiveData<Int>
    get() = _id


    fun login(request: LoginRequest):LoginResponse {
        val response=LoginResponse()

        when {
            request.username.isBlank() -> _toast.value = "กรุณาตรวจสอบอีกครั้ง"
            request.password.isBlank() -> _toast.value = "กรุณาตรวจสอบอีกครั้ง"
            request.username.length < 4 -> _toast.value = "Username <4"
            request.password.length < 4 -> _toast.value = "Password <4"
//            Patterns.EMAIL_ADDRESS.matcher(request.username).matches() -> _toast.value="กรุณาตรวจสอบอีกครั้ง"
            else -> {

                val result = DataSource.login(request)
                _login.value = result.success
                _id.value=result.userId


            }

        }
       return response


    }


}

//if (request.username.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(request.username).matches()){
//
//}
