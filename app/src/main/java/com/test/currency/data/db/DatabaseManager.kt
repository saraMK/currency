package com.test.currency.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.currency.common.Constants
import com.test.currency.domain.models.CurrencyModel


@Database(entities = [CurrencyModel::class], version = 1)
public abstract class DatabaseManager : RoomDatabase() {

    abstract fun dbQuires(): CountryDao?
    companion object {

        var instance: DatabaseManager?=null

        fun getInstance(context:Context): DatabaseManager {
            if (instance == null)
            {
                instance=Room.databaseBuilder(context.applicationContext,DatabaseManager::class.java,
                    "${Constants.DATA_BASE_NAME}").
                fallbackToDestructiveMigration().build()
            }
            return instance!!
        }
    }

}