package com.example.a01_generics

import java.lang.Exception

fun main() {
    val int = listOf(1, 2, 4, 5, 6)
    val double = listOf(1.0, 2.0, 4.0, 5.0, 6.0)
    val float = listOf(1f, 2f, 4f, 5f, 6f)
    println(getEvenElements(int))
    println(getEvenElements(double))
    println(getEvenElements(float))
    filter { getEvenElements(int) }
}

fun <T: Number> getEvenElements(elements: List<T>): List<T> {
    return elements.filter {
        when (it) {
            is Short -> it % 2 == 0
            is Int -> it % 2 == 0
            is Double -> it % 2.0 == 0.0
            is Float -> it % 2f == 0f
            else -> throw Exception("Unknown type")
        }
    }
}

 fun <T: Number> filter(callBack: (List<T>) -> List<T>): Queue<T> {
     val newObject: Queue<T> = Queue()
     callBack(newObject.elements.toList())
     return newObject
 }
