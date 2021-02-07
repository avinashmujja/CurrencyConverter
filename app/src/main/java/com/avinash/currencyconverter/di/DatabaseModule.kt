package com.avinash.currencyconverter.di

import android.content.Context
import androidx.room.Room
import com.avinash.currencyconverter.data.local.CurrencyDAO
import com.avinash.currencyconverter.data.local.CurrencyDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {
    @Singleton
    @Provides
    @JvmStatic
    fun provideDb(@ApplicationContext context : Context): CurrencyDataBase {
        return Room
            .databaseBuilder(context, CurrencyDataBase::class.java, "currency.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideDao(db: CurrencyDataBase): CurrencyDAO {
        return db.currencyDAO
    }
}