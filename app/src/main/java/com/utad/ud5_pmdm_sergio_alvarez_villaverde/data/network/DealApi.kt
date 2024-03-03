package com.utad.ud5_pmdm_sergio_alvarez_villaverde.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DealApi {

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.cheapshark.com/api/1.0/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: DealService by lazy {
        retrofit.create(DealService::class.java)
    }
}