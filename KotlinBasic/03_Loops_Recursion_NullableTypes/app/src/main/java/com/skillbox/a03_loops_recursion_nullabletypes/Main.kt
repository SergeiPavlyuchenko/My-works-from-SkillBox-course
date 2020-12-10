package com.skillbox.a03_loops_recursion_nullabletypes

import kotlin.math.abs

fun main() {

    print("Enter the number: ")
    val numbers = readLine()?.toIntOrNull() ?: return print("This is not a number")
    var count = 0
    var sum = 0
    var isDone = false
    while (!isDone) {
        for (i in 0 until numbers) {
            val number = readLine()?.toIntOrNull() ?: break
            if (number > 0) {
                count++
                sum += number
            }
            if (i == numbers - 1) isDone = true
        }
        if(!isDone) {
            println("Incorrect data. Enter $numbers numbers again.")
            count = 0
            sum = 0
        }
    }
    println("The number of positive numbers = $count\nThe sum of this numbers = $sum")
    println("\nEnter a number for \"gcd\" function")
    print("Greatest common factor = ${gcd(readLine()!!.toInt(), sum)}")

}

tailrec fun gcd(a: Int, b: Int): Int {
    return if (b == 0) abs(a) else gcd(b, a % b)
}