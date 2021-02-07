package com.avinash.currencyconverter.di

import com.avinash.currencyconverter.data.CurrencyConverterAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import com.avinash.currencyconverter.commons.Constants.API_TIMEOUT
import com.avinash.currencyconverter.commons.Constants.baseURL

@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    @JvmStatic
    fun retrofitInstance(): CurrencyConverterAPI {

        val builder = OkHttpClient.Builder()
        builder.readTimeout(API_TIMEOUT, TimeUnit.SECONDS)
        builder.writeTimeout(API_TIMEOUT, TimeUnit.SECONDS)
        builder.connectTimeout(API_TIMEOUT, TimeUnit.SECONDS)

        val requestInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val newRequest = originalRequest.newBuilder()
                .build()
            chain.proceed(newRequest)
        }
        builder.addInterceptor(requestInterceptor)

        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CurrencyConverterAPI::class.java)
    }
}