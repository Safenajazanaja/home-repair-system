package com.example.loginmvvm.presentation.homepage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginmvvm.data.datasource.DataSource
import com.example.loginmvvm.data.models.ProfileModel

class HomepageViewModel: ViewModel() {

    private val _nameModel = MutableLiveData<ProfileModel>()
    val nameModel: LiveData<ProfileModel>
        get() = _nameModel


    fun profile(userId: Int?) {
        userId?.let {
            _nameModel.value = DataSource.profile(it)
        }


    }
}