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

    private var _showProgressBar = MutableLiveData<Boolean>()
    var showProgressBar: LiveData<Boolean> = _showProgressBar

    init {
        getInterests()
    }

    private fun getInterests() {
        _showProgressBar.postValue(true)
        _interests.postLoading()

        getInterestsUseCase(
            params = Unit,
            onSuccess = { listInterestsResponse ->
                _interests.postSuccess(listInterestsResponse)
                _showProgressBar.postValue(false)
            },
            onError = {
                _interests.postError(it)
                _showProgressBar.postValue(false)

            }
        )

    }

    fun saveInterestsByUser(listIdInterests: List<String>) {
        _showProgressBar.postValue(true)
        _saveInterests.postLoading()
        saveInterestsForUserUseCase(
            params = SaveInterestsForUserUseCase.Params(
                listIdInterests = listIdInterests
            ),
            onSuccess = {
                _saveInterests.postSuccess(it)
                _showProgressBar.postValue(false)

            },
            onError = {
                _saveInterests.postError(it)
                _showProgressBar.postValue(false)

            }
        )

    }
}