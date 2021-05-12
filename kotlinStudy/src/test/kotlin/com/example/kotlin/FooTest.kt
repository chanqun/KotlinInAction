package com.example.kotlin

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class FooTest {

    @Test
    fun givenHierarchicalClass_whenMockingIt_thenReturnProperValue() {
        //given
        val foo = mockk<Foo> {
            every { name } returns "Karol"
            every { bar } returns mockk {
                every { nickname } returns "Tomato"
            }
        }

        //when
        val name = foo.name
        val nickname = foo.bar.nickname

        //then
        assertEquals("Karol", name)
        assertEquals("Tomato", nickname)
    }

    @Test
    fun givenMock_whenCapturingParamValue_thenProperValueShouldBeCaptured() {
        //given
        val service = mockk<TestableService>()
        val slot = slot<String>()
        every { service.getDataFromDb(capture(slot)) } returns "Expected Output"

        //when
        service.getDataFromDb("Expected param")

        //then
        assertEquals("Expected Param", slot.captured)
    }

    @Test
    fun givenMock_whenCapturingParamsValues_thenProperValuesShouldBeCaptured() {
        //given
        val service = mockk<TestableService>()
        val list = mutableListOf<String>()
        every { service.getDataFromDb(capture(list)) } returns "Expected Output"

        //when
        service.getDataFromDb("Expected Param 1")
        service.getDataFromDb("Expected Param 2")

        //then
        assertEquals(2, list.size)
        assertEquals("Expected Param 1", list[0])
        assertEquals("Expected Param 2", list[1])
    }
}