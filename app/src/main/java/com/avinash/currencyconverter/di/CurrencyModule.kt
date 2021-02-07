package com.avinash.currencyconverter.di

import android.content.Context
import com.avinash.currencyconverter.commons.CurrencyPreferences
import com.avinash.currencyconverter.commons.CurrencyPreferencesImpl
import com.avinash.currencyconverter.data.CurrencyConverterRepository
import com.avinash.currencyconverter.data.CurrencyConverterRepositoryImpl
import com.avinash.currencyconverter.data.repository.CurrencyConverterLocalRepository
import com.avinash.currencyconverter.data.repository.CurrencyConverterLocalRepositoryImpl
import com.avinash.currencyconverter.data.repository.CurrencyConverterNetworkRepository
import com.avinash.currencyconverter.data.repository.CurrencyConverterNetworkRepositoryImpl
import com.avinash.currencyconverter.domain.CurrencyConverterUseCase
import com.avinash.currencyconverter.domain.CurrencyConverterUseCaseImpl
import com.avinash.currencyconverter.domain.mapper.CurrencyDTOToUIMapper
import com.avinash.currencyconverter.uistate.CurrencyDetails
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(ActivityComponent::class)
@Module
abstract class CurrencyModule {

    @Binds
    abstract fun bindCurrencyConverterLocalRepository(impl : CurrencyConverterLocalRepositoryImpl) :
        CurrencyConverterLocalRepository

    @Binds
    abstract fun bindCurrencyConverterNetworkRepository(impl : CurrencyConverterNetworkRepositoryImpl) :
        CurrencyConverterNetworkRepository

    @Binds
    abstract fun bindCurrencyConverterUsecase(impl: CurrencyConverterUseCaseImpl): CurrencyConverterUseCase

    @Binds
    abstract fun bindCurrencyPreferences(impl: CurrencyPreferencesImpl) : CurrencyPreferences

    @Binds
    abstract fun bindCurrencyConverterRepository(impl : CurrencyConverterRepositoryImpl) :
        CurrencyConverterRepository

}

@InstallIn(ActivityComponent::class)
@Module
object CurrencyProviderModule {

    @Provides
    @JvmStatic
    fun bindCurrencyDTOtoUiStateMapper(@ApplicationContext context: Context):
        Function3<Map<String,Double>, String, String, List<CurrencyDetails>> = CurrencyDTOToUIMapper(context = context)
}