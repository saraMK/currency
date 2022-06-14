package com.test.currency.presentation.productsList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.currency.ConnectivityManager
import com.test.currency.common.Resource
import com.test.currency.common.ActivityState
import com.test.currency.domain.models.ConverterModel
import com.test.currency.domain.models.CurrencyModel
import com.test.currency.domain.useCase.GetCurrenciesUseCase
import com.test.currency.domain.useCase.SaveCurrenciesToDbUseCase
import com.test.currency.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    val connectivityManager: ConnectivityManager,
    val dbUseCase: SaveCurrenciesToDbUseCase,
    val useCase: GetCurrenciesUseCase,
    dispatcherIo: CoroutineDispatcher
) : BaseViewModel(dispatcherIo) {

    private var _state =
        MutableLiveData<ActivityState<List<CurrencyModel>?>>(ActivityState.NothingState)
    val state: LiveData<ActivityState<List<CurrencyModel>?>>
        get() = _state

    private var _converteState =
        MutableLiveData<ActivityState<List<ConverterModel>?>>(ActivityState.NothingState)
    val converteState: LiveData<ActivityState<List<ConverterModel>?>>
        get() = _converteState

    private var _fromCurrencyLiveData =
        MutableLiveData<String>(null)
    val fromCurrencyLiveData: LiveData<String>
        get() = _fromCurrencyLiveData
    private var _toCurrencyLiveData =
        MutableLiveData<String>(null)
    val toCurrencyLiveData: LiveData<String>
        get() = _toCurrencyLiveData

    var fromCurrency=""
    set(value) {
        field=value
        _fromCurrencyLiveData.postValue(value)
    }
    var toCurrency=""
        set(value) {
            field=value
            _toCurrencyLiveData.postValue(value)
        }



    init {
         getCurrenciesList()
    }

    fun getCurrenciesList() {
        viewModelScope.launch(dispatcherIo) {
            dbUseCase.getCurrencyList().collect {
                when (it) {
                    is Resource.Success -> {
                        _state.postValue(ActivityState.Success(data = it.data))
                    }
                    is Resource.Error -> {
                        getCurrenciesFromNetwork()
                    }
                    is Resource.Loading -> {
                        _state.postValue(ActivityState.Loading)
                    }
                }
            }
        }
    }

    fun getCurrenciesFromNetwork() {
        if (connectivityManager.isConnected()) {
            viewModelScope.launch(dispatcherIo) {
                useCase.getCurrencyList().collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            dbUseCase.saveCurrencies(result.data ?: arrayListOf())
                            _state.postValue(ActivityState.Success(data = result.data))
                        }
                        is Resource.Error -> {
                            _state.postValue(ActivityState.Error(result.message ?: ""))
                        }
                        is Resource.Loading -> {
                            _state.postValue(ActivityState.Loading)
                        }
                    }

                }


            }
        } else {
            _state.postValue(ActivityState.Error("Network teardown"))
        }
    }

    fun convertCurriencies( date: String?, endDate: String?) {
        var currencies=""
        if (fromCurrency.isNullOrEmpty() && toCurrency.isNullOrEmpty())
        {


        }else {
            currencies="${fromCurrency}_${toCurrency}"
            if (connectivityManager.isConnected()) {
                viewModelScope.launch(dispatcherIo) {
                    useCase.convertCurrencies(currencies, date, endDate).collect { result ->
                        when (result) {
                            is Resource.Success -> {
                                _converteState.postValue(ActivityState.Success(data = result.data))
                            }
                            is Resource.Error -> {
                                _converteState.postValue(ActivityState.Error(result.message ?: ""))
                            }
                            is Resource.Loading -> {
                                _converteState.postValue(ActivityState.Loading)
                            }
                        }
                    }


                }
            } else {
                _converteState.postValue(ActivityState.Error("Network teardown"))
            }
        }
    }


}