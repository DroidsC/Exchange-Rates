package com.example.cizmarcristian.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.cizmarcristian.data.local.AppSharedPreferences
import com.example.cizmarcristian.data.repository.ExchangeRepository
import com.example.cizmarcristian.data.rest.ExchangeService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UtilitiesModule {

    @Singleton
    @Provides
    fun provideExchangeService(): ExchangeService = ExchangeService()

    @Singleton
    @Provides
    fun provideExchangeRepository(exchangeService: ExchangeService) =
        ExchangeRepository(exchangeService)

    @Provides
    @Singleton
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences("com.example.cizmarcristian", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideAppSharedPreferences(preferences: SharedPreferences): AppSharedPreferences {
        return AppSharedPreferences(preferences)
    }
}