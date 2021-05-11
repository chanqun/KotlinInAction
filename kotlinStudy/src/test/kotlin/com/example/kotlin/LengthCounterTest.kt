package com.example.kotlin

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class LengthCounterTest {
    @Test
    fun getLengthCount() {
        val lengthCounter = LengthCounter()
        lengthCounter.addWord("Hello")
        assertEquals(5, lengthCounter.counter)
    }
}