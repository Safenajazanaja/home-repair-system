package com.example.loginmvvm.presentation.history.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginmvvm.data.datasource.DataSource
import com.example.loginmvvm.data.models.ImagModel
import com.example.loginmvvm.data.models.ListModel
import com.example.loginmvvm.data.request.ImagsRequest

class DetailViewModel : ViewModel() {

    private var _list = MutableLiveData<List<ListModel>>()
    val list: LiveData<List<ListModel>>
        get() = _list
    private val _imgpayModel = MutableLiveData<ImagModel>()
    val imgprofileModel: LiveData<ImagModel>
        get() = _imgpayModel

    fun listdetail(request: Int) {
        val result = DataSource.listitem(request)
        _list.value = result
    }

    fun upImg(req: ImagsRequest) {
        DataSource.upimgpay(req)
        Log.d(TAG, "chekImg: ")

    }

    fun chekImg(jobid: Int) {
        _imgpayModel.value = DataSource.chekImage(jobid)

    }

    companion object {
        private const val TAG = "www"
    }
}