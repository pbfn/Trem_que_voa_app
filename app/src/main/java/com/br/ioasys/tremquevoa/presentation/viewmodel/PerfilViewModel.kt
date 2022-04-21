package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.model.Disabilities
import com.br.ioasys.tremquevoa.domain.model.Interests
import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.domain.usecase.*
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postError
import com.br.ioasys.tremquevoa.util.postLoading
import com.br.ioasys.tremquevoa.util.postSuccess

class PerfilViewModel(
    private val getInterestsByUserUseCase: GetInterestsByUserUseCase,
    private val updateAboutMeUserUserCase: UpdateAboutMeUserUserCase,
    private val getDisabilitiesByUserUseCase: GetDisabilitiesByUserUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private var _interests = MutableLiveData<ViewState<List<Interests>>>()
    var interests: LiveData<ViewState<List<Interests>>> = _interests

    private var _deficiency = MutableLiveData<ViewState<List<Disabilities>>>()
    var deficiency: LiveData<ViewState<List<Disabilities>>> = _deficiency

    private var _user = MutableLiveData<ViewState<User>>()
    var user: LiveData<ViewState<User>> = _user


    init {
        getUser()
        getInterestsUser()
        getDeficiencyUser()
    }

    private fun getInterestsUser() {
        _interests.postLoading()
        getInterestsByUserUseCase(
            params = Unit,
            onSuccess = {
                _interests.postSuccess(it)
            },
            onError = {
                _interests.postError(it)
            }
        )
    }

    private fun getDeficiencyUser() {
        _deficiency.postLoading()
        getDisabilitiesByUserUseCase(
            params = Unit,
            onSuccess = {
                _deficiency.postSuccess(it)
            },
            onError = {
                _deficiency.postError(it)
            }
        )

    }

    private fun getUser() {
        _user.postLoading()
        getUserUseCase(
            params = Unit,
            onSuccess = {
                _user.postSuccess(it)
            },
            onError = {
                _user.postError(it)
            }
        )
    }

    fun updateAboutMe(aboutMe: String) {
        updateAboutMeUserUserCase(
            params = UpdateAboutMeUserUserCase.Params(
                aboutMe = aboutMe
            ),
            onSuccess = {

            },
            onError = {

            }
        )
    }


}