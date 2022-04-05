package com.example.cizmarcristian.model

data class CurrencyResponse(
    var success: String?,
    var symbols: Map<String, String>?,
    var error: Error? = null
)