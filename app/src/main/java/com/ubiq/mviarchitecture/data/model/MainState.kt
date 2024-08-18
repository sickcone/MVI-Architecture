package com.ubiq.mviarchitecture.data.model

sealed class MainState {
    data class MainApiSuccess(val data: MainResponse) : MainState()
    data class SubmitSuccess(val data: Boolean) : MainState()
    data class ApiError(val msg: String) : MainState()
}
