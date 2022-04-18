package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.model.Event
import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.domain.usecase.GetAllEventsUseCase
import com.br.ioasys.tremquevoa.domain.usecase.GetLocalUserUseCase
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postError
import com.br.ioasys.tremquevoa.util.postLoading
import com.br.ioasys.tremquevoa.util.postSuccess

class HomeViewModel(
     private val getAllEventsUseCase: GetAllEventsUseCase,
     private val getLocalUserUseCase: GetLocalUserUseCase
) : ViewModel() {

     private var _events = MutableLiveData<ViewState<List<Event>>>()
     var events: LiveData<ViewState<List<Event>>> = _events

     private var _user = MutableLiveData<ViewState<User>>()
     var user: LiveData<ViewState<User>> = _user

     init {
         getUserLocal()
     }

     fun getEvent(token: String) {
          _events.postLoading()

          getAllEventsUseCase(
               params = GetAllEventsUseCase.Params(
                    token = token
               ),
               onSuccess = { listInterestsResponse ->
                    _events.postSuccess(listInterestsResponse)
               },
               onError = {
                    _events.postError(it)
               }
          )
     }

     private fun getUserLocal() {
          _user.postLoading()
          getLocalUserUseCase(
               params = Unit,
               onSuccess = {
                    _user.postSuccess(it)
               },
               onError = {
                    _user.postError(it)
                    _user.postError(it)
               }
          )
     }
}