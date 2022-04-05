package com.example.cizmarcristian.data.repository

import com.example.cizmarcristian.data.local.AppSharedPreferences
import javax.inject.Inject

class PreferencesRepository
@Inject constructor(
    private val preferencesApp: AppSharedPreferences
) {

    fun saveRefreshRate(refreshRate: Int) {
        preferencesApp.saveRefreshRate(refreshRate)
    }

    fun getRefreshRate() = preferencesApp.getRefreshRate()

    fun saveMainCurrency(currency: String) {
        preferencesApp.saveMainCurrency(currency)
    }

    fun getMainCurrency() = preferencesApp.getMainCurrency()
}