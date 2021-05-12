package com.example.kotlin

class InjectTestService {
    lateinit var service1 : TestableService
    lateinit var service2 : TestableService

    fun invokeService1() : String {
        return service1.getDataFromDb("Test Param")
    }
}