package com.ubiq.mviarchitecture.data.api

import com.google.gson.JsonObject
import com.ubiq.mviarchitecture.data.model.MainResponse
import com.ubiq.mviarchitecture.util.constants.MAIN_END_POINT
import com.ubiq.mviarchitecture.util.constants.SUBMIT_END_POINT
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface MainApiClient {

    @GET
    suspend fun mainApi(
        @Url url: String = MAIN_END_POINT,
        @QueryMap map: HashMap<String, String>
    ): MainResponse

    @POST
    suspend fun submitData(
        @Url url: String = SUBMIT_END_POINT,
        @Body field: JsonObject
    ): Boolean
}