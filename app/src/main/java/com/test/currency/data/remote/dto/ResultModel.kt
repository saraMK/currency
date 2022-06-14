package com.test.currency.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ResultModel<T>(@field:SerializedName("results") val results:HashMap<String,T>)