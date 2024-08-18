package com.ubiq.mviarchitecture.domain.usecasehandler

import com.ubiq.mviarchitecture.domain.usecase.MainUseCase
import com.ubiq.mviarchitecture.domain.usecase.SubmitDataUseCase

data class MainUseCaseHandler(
    val mainUseCase: MainUseCase,
    val submitDataUseCase: SubmitDataUseCase
)
