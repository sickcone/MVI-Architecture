package com.ubiq.mviarchitecture.domain.usecase

import com.ubiq.mviarchitecture.data.model.MainResponse
import com.ubiq.mviarchitecture.data.model.MainWrapper
import com.ubiq.mviarchitecture.data.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainUseCase @Inject constructor(private val repo: MainRepository) {

    suspend operator fun invoke(): Flow<MainWrapper> = flow {
        repo.mainFetchData(
            hashMapOf()
        ).collect { apiResponse ->
            emit(handleAPIResponse(apiResponse))
        }
    }

    private fun handleAPIResponse(apiResponse: MainResponse): MainWrapper {
        return MainWrapper.MainDataSuccess(apiResponse)
    }
}