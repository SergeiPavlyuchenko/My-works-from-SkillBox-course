package com.example.a01_generics

import kotlin.random.Random

fun main() {
    val int = listOf(1, 2, 4, 5, 6)
    val double = listOf(1.0, 2.0, 4.0, 5.0, 6.0)
    val float = listOf(1f, 2f, 4f, 5f, 6f)
//    println(getEvenElements(int))
//    println(getEvenElements(double))
//    println(getEvenElements(float))

    val a: Result<Number, String> = returnIntStringObject<Int, String>()
//    val b: Result<Int, CharSequence> = returnIntStringObject<Int, CharSequence>()

    val queue = Queue(int)
    val lambdaFunction = { println("result: ${queue.filter { it % 2 == 0 }}") }
    lambdaFunction()
}


fun <T : Number> getEvenElements(elements: List<T>): List<T> {
    return elements.filter { it.toDouble() % 2 == 0.0 }
}

fun <T, R> returnIntStringObject(): Result<Int, String> {
    return if (Random.nextBoolean()) Result.Success(1) else Result.Error("Error")
}
