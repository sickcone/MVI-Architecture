package com.ubiq.mviarchitecture.data.repository

import com.google.gson.JsonObject
import com.ubiq.mviarchitecture.data.model.MainResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun mainFetchData(fieldMap: HashMap<String, String>): Flow<MainResponse>
    suspend fun submitData(field: JsonObject): Flow<Boolean>
}