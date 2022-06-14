package com.test.currency.domain.models

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.currency.common.Constants
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "${Constants.TABLE_NAME}")
data class CurrencyModel(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "country_id")
    val countryId:String,
    @ColumnInfo(name = "currency_id")
    val currencyId:String,
    @ColumnInfo(name = "currency_name")
    val currencyName:String,
    @ColumnInfo(name = "currency_symbol")
    val currencySymbol:String?,
    @ColumnInfo(name = "country_name")
    val countryName:String,

)