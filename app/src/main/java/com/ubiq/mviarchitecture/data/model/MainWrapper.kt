package com.ubiq.mviarchitecture.data.model

sealed class MainWrapper {
    data class MainDataSuccess(val successResponse: MainResponse) : MainWrapper()
    data class SubmitDataSuccess(val successResponse: Boolean) : MainWrapper()
    data class APIError(val message: String) : MainWrapper()
}
