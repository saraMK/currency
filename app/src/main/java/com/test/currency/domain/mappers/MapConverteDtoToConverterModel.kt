package com.test.currency.domain.mappers

import com.test.currency.data.remote.dto.ConverterDto
import com.test.currency.data.remote.dto.CurrencyDto
import com.test.currency.domain.models.ConverterModel
import com.test.currency.domain.models.CurrencyModel
import com.test.currency.domain.models.ValueModel

object MapConverteDtoToConverterModel {
    fun ConverterDto.mapToConverterModel(): ConverterModel = ConverterModel(
            id = id,
            fromCurrency = from,
            toCurrency = to,
            value = if (value is Double)
                listOf(ValueModel("", value))
            else
                (value as Map<String, Double>).map {
                    ValueModel(it.key, it.value)
                },
    )
}
