package com.test.currency.domain.useCase

import android.util.Log
import com.test.currency.common.Resource
import com.test.currency.data.db.CountryDao
import com.test.currency.domain.mappers.MapConverteDtoToConverterModel.mapToConverterModel
import com.test.currency.domain.mappers.MapCurrencyDtoToCurrencyModel.mapToCurrencyModel
import com.test.currency.domain.models.ConverterModel
import com.test.currency.domain.models.CurrencyModel
import com.test.currency.domain.repo.DataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SaveCurrenciesToDbUseCase @Inject constructor(val dao: CountryDao) {

    suspend fun getCurrencyList(): Flow<Resource<List<CurrencyModel>>> =
        flow {
            try {
                emit(Resource.Loading())
                val result = dao.getAll()
                Log.d("dkdkkdkdkdkd", "$result")
                if (result.isNullOrEmpty())
                    emit(Resource.Error<List<CurrencyModel>>("", null))
                else
                    emit(Resource.Success(result))
            } catch (e: Exception) {
                emit(Resource.Error<List<CurrencyModel>>(e.message ?: "", null))
            }
        }


    suspend fun saveCurrencies(list: List<CurrencyModel>) =
        CoroutineScope(Dispatchers.IO).launch {
            try {
                dao.insertAll(list)
            } catch (e: Exception) {
            }
        }
}