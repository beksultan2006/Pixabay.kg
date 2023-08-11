package com.template.pixabaykg.api

import com.template.pixabaykg.model.PixabayModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Author: Dzhaparov Bekmamat
 */
interface PixabayApi {
    @GET("api/")
    fun importPictures(
        @Query("key") key: String = "38403191-7a916555682407cbf0351ddd5",
        @Query("q") keyWord: String,
        @Query("per_page") pearPage: Int = 3,
        @Query("page") page: Int
    ): Call<PixabayModel>
}