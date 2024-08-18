package com.ubiq.mviarchitecture.di


import com.ubiq.mviarchitecture.data.api.MainApiClient
import com.ubiq.mviarchitecture.data.repository.MainRepoIml
import com.ubiq.mviarchitecture.domain.usecase.MainUseCase
import com.ubiq.mviarchitecture.domain.usecase.SubmitDataUseCase
import com.ubiq.mviarchitecture.domain.usecasehandler.MainUseCaseHandler
import com.ubiq.mviarchitecture.util.RetrofitHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Provides
    fun getMainApiClient(): MainApiClient = RetrofitHelper.getClient(MainApiClient::class.java)

    @Provides
    fun getMainUseCase(repo: MainRepoIml): MainUseCase = MainUseCase(repo)

    @Provides
    fun getSubmitDataUseCase(repo: MainRepoIml): SubmitDataUseCase = SubmitDataUseCase(repo)

    @Provides
    fun getMainRepo(client: MainApiClient): MainRepoIml = MainRepoIml(client)

    @Provides
    fun getMainUseCaseHandler(
        mainUseCase: MainUseCase,
        submitUseCase: SubmitDataUseCase
    ): MainUseCaseHandler = MainUseCaseHandler(mainUseCase, submitUseCase)

}