package com.example.cizmarcristian.data.repository

import com.example.cizmarcristian.data.rest.API_KEY
import com.example.cizmarcristian.data.rest.ExchangeService
import com.example.cizmarcristian.model.CurrencyResponse
import io.reactivex.Single
import javax.inject.Inject

class ExchangeRepository @Inject constructor(private val exchangeService: ExchangeService) {

    fun getCurrencies(): Single<CurrencyResponse> =
        exchangeService.getCurrencies(API_KEY)

    fun getExchangeRates(baseCurrency: String) =
        exchangeService.getExchangeRates(API_KEY, baseCurrency)

    fun getHistoricRates(
        date: String,
        baseCurrency: String,
        currencies: String
    ) = exchangeService.getHistoricRates(date, API_KEY, baseCurrency, currencies)
}