package com.test.currency.di

import android.content.Context
import com.test.currency.data.db.CountryDao
import com.test.currency.data.db.DatabaseManager
import com.test.currency.data.repo.DataRepositoryImpl
import com.test.currency.domain.repo.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getRepo(repo: DataRepositoryImpl): DataRepository = repo


    @Singleton
    @Provides
    fun getDispatcher(): CoroutineDispatcher = Dispatchers.IO


    @Provides
    @Singleton
    fun getDbQuire(@ApplicationContext context: Context): CountryDao =
        DatabaseManager.getInstance(context).dbQuires()!!


}