package com.example.cizmarcristian.model

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

class ExchangeRateResponseTest {

    lateinit var exchangeRateResponse: ExchangeRate

    @Before
    fun setup() {
        exchangeRateResponse = ExchangeRate(null, null, null, null, null, null)
    }

    @Test
    fun testGetSuccess() {
        val success = Random.nextBoolean()
        exchangeRateResponse.success = success
        Assert.assertEquals(success, exchangeRateResponse.success)
    }

    @Test
    fun testGetTimestamp() {
        val timestamp = Random.nextLong()
        exchangeRateResponse.timestamp = timestamp
        Assert.assertEquals(timestamp, exchangeRateResponse.timestamp)
    }

    @Test
    fun testGetBase() {
        val base = ('a'..'z').random().toString().repeat(3)
        exchangeRateResponse.base = base
        Assert.assertEquals(base, exchangeRateResponse.base)
    }

    @Test
    fun testGetDate() {
        val date = listOf("12-12-2012", "14-04-2014", "20-02-2022").random()
        exchangeRateResponse.date = date
        Assert.assertEquals(date, exchangeRateResponse.date)
    }

    @Test
    fun testGetRates() {
        val rates = mapOf("EUR" to 5f)
        exchangeRateResponse.rates = rates
        Assert.assertEquals(rates, exchangeRateResponse.rates)
    }

    @Test
    fun testGetError() {
        val error = Error(400, "error_type", "error_info")
        exchangeRateResponse.error = error
        Assert.assertEquals(error, exchangeRateResponse.error)
    }
}