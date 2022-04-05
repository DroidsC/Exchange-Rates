package com.example.cizmarcristian.model

data class ExchangeRateHistoric(
    var success: Boolean?,
    var historical: Boolean?,
    var date: String?,
    var timestamp: Long?,
    var base: String?,
    var rates: Map<String, Float>?,
    var error: Error? = null
)