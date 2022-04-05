package com.example.cizmarcristian.model

data class ExchangeRate(
    var success: Boolean?,
    var timestamp: Long?,
    var base: String?,
    var date: String?,
    var rates: Map<String, Float>?,
    var error: Error? = null
)