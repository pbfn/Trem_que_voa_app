package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.util.ViewState

class LoginViewModel(

):ViewModel() {

    private var _user = MutableLiveData<ViewState<Boolean>>()
    var user: LiveData<ViewState<Boolean>> = _user


    fun doLogin(email: String, password: String) {
        _user.postValue(ViewState.Loading)
    }

}