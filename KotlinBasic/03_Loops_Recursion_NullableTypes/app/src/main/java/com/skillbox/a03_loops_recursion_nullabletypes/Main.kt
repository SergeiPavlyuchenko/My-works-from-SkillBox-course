package com.skillbox.a03_loops_recursion_nullabletypes

import kotlin.math.abs

fun main() {

    print("Enter the number: ")
    val numbers = inputInt()
    var positiveCount = 0
    var sum = 0
    repeat(numbers) {
        val number = inputInt()
        if (number > 0) {
            positiveCount++
            sum += number
            }
        }
    println("The number of positive numbers = $positiveCount\nThe sum of this numbers = $sum")
    println("\nEnter a number for \"gcd\" function")
    print("Greatest common factor = ${ gcd(inputInt(), sum) }")

}

tailrec fun gcd(a: Int, b: Int): Int {
    return if (b == 0) abs(a) else gcd(b, a % b)
}

tailrec fun inputInt(): Int {
    return readLine()?.toIntOrNull() ?: inputInt()
}