package com.example.cizmarcristian.model

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CurrencyResponseTest {

    lateinit var currencyResponse: CurrencyResponse

    @Before
    fun setup() {
        currencyResponse = CurrencyResponse(null, null, null)
    }

    @Test
    fun testGetSuccess() {
        val success = ('a'..'z').random().toString()
        currencyResponse.success = success
        assertEquals(success, currencyResponse.success)
    }

    @Test
    fun testGetSymbols() {
        val symbol = mapOf("USD" to "American Dollar")
        currencyResponse.symbols = symbol
        assertEquals(symbol, currencyResponse.symbols)
    }

    @Test
    fun testGetError() {
        val error = Error(400, "error_type", "error_info")
        currencyResponse.error = error
        assertEquals(error, currencyResponse.error)
    }
}