package com.test.currency.data.remote

import androidx.annotation.Nullable
import com.test.currency.common.Resource
import com.test.currency.data.remote.dto.ConverterDto
import com.test.currency.data.remote.dto.CurrencyDto
import com.test.currency.data.remote.dto.ResultModel
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface NetworkApi {

    @GET("countries")
    suspend fun getCurrencyList(
    ): ResultModel<CurrencyDto>

    @GET("convert")
    suspend fun convertCurrency(
            @Query("q") query:String,
            @Nullable @Query("date") date:String?,
            @Nullable @Query("endDate") endDate:String?,
    ): ResultModel<ConverterDto>
}