package com.ubiq.mviarchitecture.data.repository

import com.google.gson.JsonObject
import com.ubiq.mviarchitecture.data.api.MainApiClient
import com.ubiq.mviarchitecture.data.model.MainResponse
import com.ubiq.mviarchitecture.util.RetrofitHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepoIml @Inject constructor(private val client: MainApiClient) : MainRepository {

    override suspend fun mainFetchData(fieldMap: HashMap<String, String>): Flow<MainResponse> {
        return RetrofitHelper.doApiCall {
            client.mainApi(map = fieldMap)
        }
    }

    override suspend fun submitData(field: JsonObject): Flow<Boolean> {
        return RetrofitHelper.doApiCall {
            client.submitData(field = JsonObject())
        }
    }
}