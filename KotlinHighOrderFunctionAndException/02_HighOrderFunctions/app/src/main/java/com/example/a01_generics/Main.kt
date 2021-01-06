package com.example.a01_generics

import java.lang.Exception

fun main() {
    val int = listOf(1, 2, 4, 5, 6)
    val double = listOf(1.0, 2.0, 4.0, 5.0, 6.0)
    val float = listOf(1f, 2f, 4f, 5f, 6f)
    println(getEvenElements(int))
    println(getEvenElements(double))
    println(getEvenElements(float))

    val lambdaFunction = { println("result: ${Queue<Int>().filter { getEvenElements(int) }}") }
    lambdaFunction()

}


fun <T : Number> getEvenElements(elements: List<T>): List<T> {
    return elements.filter { it.toDouble() % 2 == 0.0 }
}

fun returnIntStringObject(item: Result<Int, String>): Result<Int, String> {
    return item
}