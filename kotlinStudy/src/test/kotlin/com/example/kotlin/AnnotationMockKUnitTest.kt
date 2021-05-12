package com.example.kotlin

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.BeforeEach

class AnnotationMockKUnitTest {

    @MockK
    lateinit var service1: TestableService

    @MockK
    lateinit var service2: TestableService

    @InjectMockKs
    var objectUnderTest = InjectTestService()

    @BeforeEach
    fun setUp() = MockKAnnotations.init(this)
}