package com.example.kotlin

import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class TestableServiceTest {
    @Test
    fun givenServiceMock_whenCallingMockedMethod_thenCorrectlyVerified() {
        //given
        val service = mockk<TestableService>()
        every { service.getDataFromDb("Expected Param") } returns "Expected Output"

        //when
        val result = service.getDataFromDb("Expected Param")

        //then
        verify { service.getDataFromDb("Expected Param") }
        assertEquals("Expected Output", result)
    }

    @Test
    fun givenServiceSpy_whenMockingOnlyOneMethod_thenOtherMethodsShouldBehaveAsOriginalObject() {
        //given
        val service = spyk<TestableService>()
        every { service.getDataFromDb(any()) } returns "Mocked Output"

        //when checking mocked method
        val firstResult = service.getDataFromDb("Any Param")

        //then
        assertEquals("Mocked Output", firstResult)

        //when checking not mocked method
        val secondResult = service.doSomethingElse("Any Param")

        //then
        assertEquals("I don't want to!", secondResult)
    }

    @Test
    fun givenRelaxedMock_whenCallingNotMockedMethod_thenReturnDefaultValue() {
        //given -> default 값을 가짐 String은 empty String
        val service = mockk<TestableService>(relaxed = true)

        //when
        val result = service.getDataFromDb("Any Param")

        //then
        assertEquals("", result)
    }
}