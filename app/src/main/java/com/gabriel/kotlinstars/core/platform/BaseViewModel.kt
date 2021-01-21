package com.gabriel.kotlinstars.core.platform

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    private var _failure: MutableLiveData<String?> = MutableLiveData()
    var failure: LiveData<String?> = _failure

    protected fun handleFailure(failure: String?) {
        _failure.postValue(failure)
    }
}