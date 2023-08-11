package com.template.pixabaykg.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Author: Dzhaparov Bekmamat
 */
object RetrofitService {
    var retrofit: Retrofit = Retrofit.Builder().baseUrl("https://pixabay.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()
    var api: PixabayApi = retrofit.create(PixabayApi::class.java)
}