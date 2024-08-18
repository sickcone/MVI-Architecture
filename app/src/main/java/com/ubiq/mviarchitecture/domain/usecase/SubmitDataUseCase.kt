package com.ubiq.mviarchitecture.domain.usecase

import com.google.gson.JsonObject
import com.ubiq.mviarchitecture.data.model.MainWrapper
import com.ubiq.mviarchitecture.data.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SubmitDataUseCase @Inject constructor(private val repo: MainRepository) {

    suspend operator fun invoke(data: JsonObject): Flow<MainWrapper> = flow {
        repo.submitData(data).collect { apiResponse ->
            emit(getSuccessResponse(apiResponse))
        }
    }

    private fun getSuccessResponse(apiResponse: Boolean): MainWrapper {
        return MainWrapper.SubmitDataSuccess(apiResponse)
    }
}