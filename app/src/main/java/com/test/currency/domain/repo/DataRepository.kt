package com.test.currency.domain.repo

import com.test.currency.data.remote.dto.ConverterDto
import com.test.currency.data.remote.dto.CurrencyDto
import com.test.currency.data.remote.dto.ResultModel

interface DataRepository {
    suspend fun getCurrencyList(): ResultModel<CurrencyDto>
    suspend fun  getConverter(currencies: String, date: String?,endDate:String?): ResultModel<ConverterDto>
}