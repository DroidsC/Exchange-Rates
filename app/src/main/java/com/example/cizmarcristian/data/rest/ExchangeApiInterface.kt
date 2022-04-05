package com.example.cizmarcristian.data.rest

import com.example.cizmarcristian.model.CurrencyResponse
import com.example.cizmarcristian.model.ExchangeRateHistoric
import com.example.cizmarcristian.model.ExchangeRate
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ExchangeApiInterface {

    @GET("/symbols")
    fun getCurrencies(
        @Query("access_key") apikey: String
    ): Single<CurrencyResponse>

    @GET("/latest?")
    fun getExchangeRates(
        @Query("access_key") apikey: String,
        @Query("base") baseCurrency: String
    ): Single<ExchangeRate>

    @GET("/{date}")
    fun getHistoricRates(
        @Path("date") date: String,
        @Query("access_key") apikey: String,
        @Query("base") baseCurrency: String,
        @Query("symbols") currencies: String,
    ): Single<ExchangeRateHistoric>
}