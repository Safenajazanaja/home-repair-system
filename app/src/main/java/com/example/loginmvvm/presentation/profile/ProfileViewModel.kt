package com.example.loginmvvm.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginmvvm.data.datasource.DataSource
import com.example.loginmvvm.data.models.ProfileModel

class ProfileViewModel : ViewModel() {

    private val _profileModel = MutableLiveData<ProfileModel>()
    val profileModel: LiveData<ProfileModel>
        get() = _profileModel

    fun profile(userId: Int?) {
        userId?.let {
            _profileModel.value = DataSource.profile(it)
        }

    }

}
