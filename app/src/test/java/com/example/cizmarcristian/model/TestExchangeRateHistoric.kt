package com.example.cizmarcristian.model

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

class TestExchangeRateHistoric {

    lateinit var exchangeRateHistoric: ExchangeRateHistoric

    @Before
    fun setup() {
        exchangeRateHistoric = ExchangeRateHistoric(null, null, null, null, null, null, null)
    }

    @Test
    fun testGetSuccess() {
        val success = Random.nextBoolean()
        exchangeRateHistoric.success = success
        assertEquals(success, exchangeRateHistoric.success)
    }

    @Test
    fun testGetHistorical() {
        val historical = Random.nextBoolean()
        exchangeRateHistoric.historical = historical
        assertEquals(historical, exchangeRateHistoric.historical)
    }

    @Test
    fun testGetDate() {
        val date = listOf("12-12-2012", "14-04-2014", "20-02-2022").random()
        exchangeRateHistoric.date = date
        assertEquals(date, exchangeRateHistoric.date)
    }

    @Test
    fun testGetTimestamp() {
        val timestamp = Random.nextLong()
        exchangeRateHistoric.timestamp = timestamp
        assertEquals(timestamp, exchangeRateHistoric.timestamp)
    }

    @Test
    fun testGetBase() {
        val base = ('a'..'z').random().toString().repeat(3)
        exchangeRateHistoric.base = base
        assertEquals(base, exchangeRateHistoric.base)
    }

    @Test
    fun testGetRates() {
        val rates = mapOf("EUR" to 5f)
        exchangeRateHistoric.rates = rates
        assertEquals(rates, exchangeRateHistoric.rates)
    }

    @Test
    fun testGetError() {
        val error = Error(400, "error_type", "error_info")
        exchangeRateHistoric.error = error
        assertEquals(error, exchangeRateHistoric.error)
    }
}