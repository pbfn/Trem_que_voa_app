package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.model.Interests
import com.br.ioasys.tremquevoa.domain.usecase.GetInterestsUseCase
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postError
import com.br.ioasys.tremquevoa.util.postLoading
import com.br.ioasys.tremquevoa.util.postSuccess

class InterestsViewModel(
    private val getInterestsUseCase: GetInterestsUseCase
) : ViewModel() {

    private var _interests = MutableLiveData<ViewState<List<Interests>>>()
    var interests: LiveData<ViewState<List<Interests>>> = _interests


    fun getInterests() {
        _interests.postLoading()

        getInterestsUseCase(
            params = GetInterestsUseCase.Params(
                userID = null
            ),
            onSuccess = { listInterestsResponse ->
                _interests.postSuccess(listInterestsResponse)
            },
            onError = {
                _interests.postError(it)
            }
        )

    }

}