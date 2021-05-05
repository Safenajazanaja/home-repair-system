package com.example.loginmvvm.presentation.history.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginmvvm.data.datasource.DataSource
import com.example.loginmvvm.data.models.ListModel
import com.example.loginmvvm.data.models.OrderModeldetail

class DetailViewModel: ViewModel() {

    private var _list = MutableLiveData<List<ListModel>>()
    val list: LiveData<List<ListModel>>
        get() = _list


    fun listdetail(request:Int){
        val result=DataSource.listitem(request)
        _list.value=result
    }
}