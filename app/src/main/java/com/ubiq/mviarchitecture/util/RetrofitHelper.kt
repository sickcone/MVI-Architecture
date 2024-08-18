package com.ubiq.mviarchitecture.util

import com.ubiq.mviarchitecture.util.constants.BASE_URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private val getRetrofitInstance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <Service> getClient(clientClass: Class<Service>): Service =
        getRetrofitInstance.create(clientClass)

    suspend inline fun <T, Base : T> doApiCall(crossinline apiCall: suspend () -> Base): Flow<T> {
        return flow {
            emit(apiCall.invoke())
        }.flowOn(Dispatchers.IO)
    }
}