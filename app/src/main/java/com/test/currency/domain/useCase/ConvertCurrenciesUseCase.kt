package com.test.currency.domain.useCase

import com.test.currency.common.Resource
import com.test.currency.domain.mappers.MapConverteDtoToConverterModel.mapToConverterModel
import com.test.currency.domain.mappers.MapCurrencyDtoToCurrencyModel.mapToCurrencyModel
import com.test.currency.domain.models.ConverterModel
import com.test.currency.domain.models.CurrencyModel
import com.test.currency.domain.repo.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.reflect.Type
import javax.inject.Inject

class ConvertCurrenciesUseCase @Inject constructor(val repo: DataRepository) {

     suspend fun convertCurrencies(currencies: String, date: String?,endDate:String?):
             Flow<Resource<List<ConverterModel>>> = flow {
            try {
                emit(Resource.Loading())
                val result = repo.getConverter(currencies,date,endDate)
                val mapper = result.results.values.map { it.mapToConverterModel() }
              emit(Resource.Success(mapper))
            } catch (e: Exception) {
                emit(Resource.Error<List<ConverterModel>>(e.message?:"",null))
            }
       }

    }