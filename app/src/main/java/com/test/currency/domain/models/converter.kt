package com.test.currency.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class ConverterModel(
    val id:String,
    val fromCurrency:String,
    val toCurrency:String,
    val value:List<ValueModel>,
)

data class ValueModel(
    val date:String,
    val value:Double,
)