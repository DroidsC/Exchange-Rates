package com.example.cizmarcristian.data.local

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

private const val KEY_REFRESH_RATE_SECONDS = "com.example.cizmarcristian.refreshRateSeconds"
private const val KEY_MAIN_CURRENCY = "com.example.cizmarcristian.mainCurrency"

class AppSharedPreferences @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun saveRefreshRate(refreshRate: Int) {
        sharedPreferences.edit { putInt(KEY_REFRESH_RATE_SECONDS, refreshRate) }
    }

    fun getRefreshRate() = sharedPreferences.getInt(KEY_REFRESH_RATE_SECONDS, 3)

    fun saveMainCurrency(currency: String) {
        sharedPreferences.edit { putString(KEY_MAIN_CURRENCY, currency) }
    }

    fun getMainCurrency() = sharedPreferences.getString(KEY_MAIN_CURRENCY, "EUR")
}