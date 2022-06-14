package com.test.currency.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CurrencyDto(@field:SerializedName("id") val countryId:String,
                       @field:SerializedName("currencyName")  val currencyName:String,
                       @field:SerializedName("currencySymbol")  val symbol:String?,
                       @field:SerializedName("currencyId")  val currencyId:String,
                       @field:SerializedName("name")  val countryName:String,
)