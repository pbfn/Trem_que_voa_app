package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.model.*
import com.br.ioasys.tremquevoa.domain.usecase.GetAllEventsUseCase
import com.br.ioasys.tremquevoa.domain.usecase.GetDisabilitiesUseCase
import com.br.ioasys.tremquevoa.domain.usecase.GetInterestsUseCase
import com.br.ioasys.tremquevoa.domain.usecase.GetLocalUserUseCase
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postError
import com.br.ioasys.tremquevoa.util.postLoading
import com.br.ioasys.tremquevoa.util.postSuccess

class HomeViewModel(
     private val getAllEventsUseCase: GetAllEventsUseCase,
     private val getLocalUserUseCase: GetLocalUserUseCase,
     private val getInterestsUseCase: GetInterestsUseCase,
     private val getDisabilitiesUseCase: GetDisabilitiesUseCase
) : ViewModel() {

     private var _events = MutableLiveData<ViewState<EventLists>>()
     var events: LiveData<ViewState<EventLists>> = _events

     private var _user = MutableLiveData<ViewState<User>>()
     var user: LiveData<ViewState<User>> = _user

     private var _interest = MutableLiveData<ViewState<List<Interests>>>()
     var interest: LiveData<ViewState<List<Interests>>> = _interest

     private var _disabilities = MutableLiveData<ViewState<List<Disabilities>>>()
     var disabilities: LiveData<ViewState<List<Disabilities>>> = _disabilities

     init {
         getUserLocal()
     }

     fun getEvent(token: String) {
          _events.postLoading()

          getAllEventsUseCase(
               params = GetAllEventsUseCase.Params(
                    token = token
               ),
               onSuccess = { listEvent ->
                    _events.postSuccess(listEvent.let { list ->
                         EventLists(
                              listOnline = list.filter {
                                   it.isOnline
                              },
                              listPromoted = list.filter {
                                   it.isPromoted
                              },
                              listRecommended = list.filter {
                                   it.isOnline.not()
                              }
                         )
                    })
               },
               onError = {
                    _events.postError(it)
               }
          )
     }

     fun getInterest(token: String) {
          _interest.postLoading()

          getInterestsUseCase(
               params = GetInterestsUseCase.Params(
                    token = token
               ),
               onSuccess = { listInterest ->
                    _interest.postSuccess(listInterest)
               },
               onError = {
                    _interest.postError(it)
               }
          )
     }

     fun getDisabilities(token: String) {
          _disabilities.postLoading()

          getDisabilitiesUseCase(
               params = GetDisabilitiesUseCase.Params(
                    token = token
               ),
               onSuccess = { listDisabilities ->
                    _disabilities.postSuccess(listDisabilities)
               },
               onError = {
                    _disabilities.postError(it)
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