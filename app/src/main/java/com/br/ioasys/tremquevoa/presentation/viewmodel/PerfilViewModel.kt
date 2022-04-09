package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.model.Interests
import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.domain.usecase.GetInterestsByUserUseCase
import com.br.ioasys.tremquevoa.domain.usecase.GetInterestsUseCase
import com.br.ioasys.tremquevoa.domain.usecase.GetLocalUserUseCase
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postError
import com.br.ioasys.tremquevoa.util.postLoading
import com.br.ioasys.tremquevoa.util.postSuccess

class PerfilViewModel(
    private val getLocalUserUseCase: GetLocalUserUseCase,
    private val getInterestsByUserUseCase: GetInterestsByUserUseCase
) : ViewModel() {

    private var _interests = MutableLiveData<ViewState<List<Interests>>>()
    var interests: LiveData<ViewState<List<Interests>>> = _interests

    private var _user = MutableLiveData<ViewState<User>>()
    var user: LiveData<ViewState<User>> = _user

    init {
        getUserLocal()
    }

    private fun getInterestsUser(token: String) {
        _interests.postLoading()
        getInterestsByUserUseCase(
            GetInterestsByUserUseCase.Params(
                token = token
            ),
            onSuccess = {
                _interests.postSuccess(it)
            },
            onError = {
                _interests.postError(it)
            }
        )
    }

    private fun getUserLocal() {
        _user.postLoading()
        getLocalUserUseCase(
            params = Unit,
            onSuccess = {
                _user.postSuccess(it)
                getInterestsUser(it.token)
            },
            onError = {
                _user.postError(it)
                _interests.postError(it)
            }
        )
    }


}