package com.test.currency.domain.mappers

import com.test.currency.data.remote.dto.CurrencyDto
import com.test.currency.domain.models.CurrencyModel

object MapCurrencyDtoToCurrencyModel {
    fun CurrencyDto.mapToCurrencyModel(): CurrencyModel = CurrencyModel(
        currencyId = currencyId,
        currencyName = currencyName,
        currencySymbol = symbol,
        countryName = countryName,
        countryId = countryId,
    )
}
