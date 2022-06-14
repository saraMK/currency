package com.test.currency.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.currency.common.Constants
import com.test.currency.domain.models.CurrencyModel

@Dao
interface CountryDao {

    @Query("SELECT * FROM ${Constants.TABLE_NAME}")
    fun getAll(): List<CurrencyModel>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(todo: List<CurrencyModel>)

}