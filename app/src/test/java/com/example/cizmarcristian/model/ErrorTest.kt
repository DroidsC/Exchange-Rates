package com.example.cizmarcristian.model

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

class ErrorTest {

    lateinit var error: Error

    @Before
    fun setup() {
        error = Error(null, null, null)
    }

    @Test
    fun testGetCode() {
        val code = Random.nextInt()
        error.code = code
        assertEquals(code, error.code)
    }

    @Test
    fun testGetType() {
        val type = ('a'..'z').random().toString()
        error.type = type
        assertEquals(type, error.type)
    }

    @Test
    fun testGetInfo() {
        val info = ('a'..'z').random().toString()
        error.info = info
        assertEquals(info, error.info)
    }
}