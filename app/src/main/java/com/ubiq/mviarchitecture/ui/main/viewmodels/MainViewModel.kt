package com.ubiq.mviarchitecture.ui.main.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.ubiq.mviarchitecture.data.model.DataClassOne
import com.ubiq.mviarchitecture.data.model.DataClassTwo
import com.ubiq.mviarchitecture.data.model.MainState
import com.ubiq.mviarchitecture.data.model.MainWrapper
import com.ubiq.mviarchitecture.domain.usecasehandler.MainUseCaseHandler
import com.ubiq.mviarchitecture.intent.MainIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainUseCaseHandler: MainUseCaseHandler) :
    ViewModel() {

    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)

    private val _items = mutableStateListOf<DataClassOne>()
    val items: List<DataClassOne> = _items

    private val response = MutableLiveData<MainState>()
    fun getResponseState(): LiveData<MainState> = response

    private val callApiButton = MutableLiveData<DataClassOne>()
    fun getCallApiButtonClick(): LiveData<DataClassOne> = callApiButton

    private val submitButton = MutableLiveData<DataClassTwo>()
    fun getSubmitButtonClick(): LiveData<DataClassTwo> = submitButton


    init {
        handleUserIntent()
    }

    private fun handleUserIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.MainCall -> {
                        handleMainCall()
                    }

                    is MainIntent.SubmitClicked -> {
                        handleSubmitCall()
                    }
                }
            }
        }
    }

    private fun handleMainCall() {
        viewModelScope.launch {
            mainUseCaseHandler.mainUseCase.invoke().collect {
                handleApiResponse(it)
            }
        }
        callApiButton.postValue(DataClassOne("", ""))
    }

    private fun handleSubmitCall() {
        viewModelScope.launch {
            mainUseCaseHandler.submitDataUseCase.invoke(JsonObject()).collect {
                handleApiResponse(it)
            }
        }
        submitButton.postValue(DataClassTwo(1))
    }

    private fun handleApiResponse(it: MainWrapper) {
        when (it) {
            is MainWrapper.MainDataSuccess -> {
                for (element in it.successResponse) {
                    _items.add(element)
                }
                response.postValue(MainState.MainApiSuccess(it.successResponse))
            }

            is MainWrapper.SubmitDataSuccess -> {
                response.postValue(MainState.SubmitSuccess(it.successResponse))
            }

            is MainWrapper.APIError -> {
                response.postValue(MainState.ApiError(it.message))
            }
        }
    }

}