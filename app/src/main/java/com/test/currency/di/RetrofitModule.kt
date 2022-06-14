package com.test.currency.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.test.currency.common.Constants
import com.test.currency.common.Constants.BASE_URL
import com.test.currency.data.remote.NetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DateFormat
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {


    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)

        }
    }

    @Singleton
    @Provides
    fun provideRequestInterceptor(): Interceptor {
        return Interceptor { chain ->

            val request = chain.request()
                .newBuilder()
            val originalHttpUrl = chain.request().url()
            val url = originalHttpUrl.newBuilder().
            addQueryParameter("apiKey", Constants.API_KEY).build()
            request.url(url)
            val response = chain.proceed(request.build())
            return@Interceptor response
        }
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        requestInterceptor: Interceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(requestInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesGson(): Gson {
        return GsonBuilder()
            .enableComplexMapKeySerialization()
            .serializeNulls()
            .setDateFormat(DateFormat.LONG)
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting()
            .setVersion(1.0)
            .create()

    }

    @Singleton
    @Provides
    fun providesRetrofitClient(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
       return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideNetworkApiService(retrofit: Retrofit): NetworkApi {
        return retrofit.create(NetworkApi::class.java)
    }
}