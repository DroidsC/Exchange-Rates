package com.example.cizmarcristian.util

import com.example.cizmarcristian.model.Error

fun generateErrorText(error: Error?): String {
    val errorInfoList = mutableListOf<String>()
    with(errorInfoList) {
        error?.code?.let { add(it.toString()) }
        error?.info?.let { add(it) }
    }
    return if (errorInfoList.size > 0) {
        "Error: " + errorInfoList.joinToString(", ")
    } else {
        "Something went wrong..."
    }
}