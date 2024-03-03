package com.utad.ud5_pmdm_sergio_alvarez_villaverde.data.network

import com.utad.ud5_pmdm_sergio_alvarez_villaverde.data.network.model.Deal
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DealService {

    @GET("deals")
    suspend fun getDealsByStoreID(@Query("storeID") storeID: String): Response<List<Deal>>
}