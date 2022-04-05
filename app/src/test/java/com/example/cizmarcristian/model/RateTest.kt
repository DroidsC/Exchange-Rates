package com.example.cizmarcristian.model

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

class RateTest {

    lateinit var rate: Rate

    @Before
    fun setup() {
        rate = Rate(null, null)
    }

    @Test
    fun testGetName() {
        val name = ('a'..'z').random().toString()
        rate.name = name
        assertEquals(name, rate.name)
    }

    @Test
    fun testGetRate() {
        val rateValue = Random.nextFloat()
        rate.rate = rateValue
        assertEquals(rateValue, rate.rate)
    }
}