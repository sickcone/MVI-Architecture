package com.ubiq.mviarchitecture.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.ubiq.mviarchitecture.util.constants.DATA
import com.ubiq.mviarchitecture.util.constants.EMPTY
import com.ubiq.mviarchitecture.util.constants.MESSAGE
import com.ubiq.mviarchitecture.util.constants.RESPONSE_CODE
import com.ubiq.mviarchitecture.util.constants.SUCCESS
import com.ubiq.mviarchitecture.util.constants.SUCCESS_CODE_BOOLEAN
import com.ubiq.mviarchitecture.util.constants.SUCCESS_CODE_INT
import com.ubiq.mviarchitecture.util.constants.SUCCESS_CODE_STRING

@Keep
open class BaseResponse<T>(
    private val status: String?,
    @SerializedName(SUCCESS) private val success: Any?,
    @SerializedName(RESPONSE_CODE) val responseCode: Any?,
    @SerializedName(DATA) private val responseData: T?,
    @SerializedName(MESSAGE) protected val message: String?
) {

    private val statusCodes =
        listOf(SUCCESS_CODE_STRING, true, SUCCESS_CODE_BOOLEAN, 1, SUCCESS_CODE_INT)

    fun getStatus(): String = status ?: EMPTY

    fun getResponseSuccessStatus(): Boolean = statusCodes.contains(success)

    fun getResponseCode(): String = responseCode?.toString() ?: EMPTY

    fun getResponseData(): T? = responseData

    fun getResponseMessage(): String = if (message.isNullOrEmpty().not()) {
        message
    } else {
        responseCode?.toString()
    } ?: EMPTY

}