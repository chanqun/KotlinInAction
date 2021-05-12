package com.example.kotlin

class TestableService {
    fun getDataFromDb(testParameter: String): String {
        //query database and return matching value
        return "0"
    }

    fun doSomethingElse(testParameter: String): String {
        return "I don't want to!"
    }
}