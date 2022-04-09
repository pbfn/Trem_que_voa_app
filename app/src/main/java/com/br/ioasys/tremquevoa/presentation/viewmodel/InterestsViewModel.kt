package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.model.Interests
import com.br.ioasys.tremquevoa.domain.usecase.GetInterestsUseCase
import com.br.ioasys.tremquevoa.domain.usecase.SaveInterestsForUserUseCase
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postError
import com.br.ioasys.tremquevoa.util.postLoading
import com.br.ioasys.tremquevoa.util.postSuccess

class InterestsViewModel(
    private val getInterestsUseCase: GetInterestsUseCase,
    private val saveInterestsForUserUseCase: SaveInterestsForUserUseCase
) : ViewModel() {

    private var _interests = MutableLiveData<ViewState<List<Interests>>>()
    var interests: LiveData<ViewState<List<Interests>>> = _interests

    private var _saveInterests = MutableLiveData<ViewState<Boolean>>()
    var saveInterests: LiveData<ViewState<Boolean>> = _saveInterests


    fun getInterests(token: String) {
        _interests.postLoading()

        getInterestsUseCase(
            params = GetInterestsUseCase.Params(
                token = token
            ),
            onSuccess = { listInterestsResponse ->
                _interests.postSuccess(listInterestsResponse)
            },
            onError = {
                _interests.postError(it)
            }
        )

    }

    fun saveInterestsByUser(token: String, listIdInterests: List<String>) {
        _saveInterests.postLoading()

        saveInterestsForUserUseCase(
            params = SaveInterestsForUserUseCase.Params(
                token = token,
                listIdInterests = listIdInterests
            ),
            onSuccess = {
                _saveInterests.postSuccess(it)
            },
            onError = {
                _saveInterests.postError(it)
            }
        )

    }
}