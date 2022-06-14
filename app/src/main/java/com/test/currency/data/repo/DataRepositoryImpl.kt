package com.test.currency.data.repo

import com.test.currency.data.remote.NetworkApi
import com.test.currency.data.remote.dto.ConverterDto
import com.test.currency.data.remote.dto.CurrencyDto
import com.test.currency.data.remote.dto.ResultModel
import com.test.currency.domain.repo.DataRepository
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(val api: NetworkApi): DataRepository
{
    override suspend fun getCurrencyList(): ResultModel<CurrencyDto> {
        return api.getCurrencyList()
    }

    override suspend fun  getConverter(currencies :String,date:String?,endDate:String?): ResultModel<ConverterDto> {
        return api.convertCurrency(currencies,date,endDate)
    }
}