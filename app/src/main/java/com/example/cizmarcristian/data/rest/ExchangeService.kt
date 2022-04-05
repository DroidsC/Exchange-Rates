package com.example.cizmarcristian.data.rest

import com.example.cizmarcristian.model.CurrencyResponse
import com.example.cizmarcristian.model.ExchangeRateHistoric
import com.google.gson.GsonBuilder
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

private const val BASE_URL = "http://api.exchangeratesapi.io/v1/"
const val API_KEY = "99caa9e7b0b68499d87d284020eb1b6f"

class ExchangeService @Inject constructor() : ExchangeApiInterface {

    private val exchangeApi: ExchangeApiInterface by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        retrofit.create(ExchangeApiInterface::class.java)
    }

    override fun getCurrencies(apikey: String): Single<CurrencyResponse> =
        exchangeApi.getCurrencies(apikey)

    override fun getExchangeRates(apikey: String, baseCurrency: String) =
        exchangeApi.getExchangeRates(apikey, baseCurrency)

    override fun getHistoricRates(
        date: String,
        apikey: String,
        baseCurrency: String,
        currencies: String
    ): Single<ExchangeRateHistoric> =
        exchangeApi.getHistoricRates(date, apikey, baseCurrency, currencies)
}