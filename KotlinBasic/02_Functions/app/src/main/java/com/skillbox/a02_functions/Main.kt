package com.skillbox.a02_functions

import kotlin.math.sqrt

fun main() {
    val solutionSum = solveEquation(a = 3, b = -14, c = -5)
    print("Sum = $solutionSum")
}

fun solveEquation (a: Int, b: Int, c: Int): Double {
    val d = (b * b - 4 * a * c).toDouble()  // calculate discriminant
    var sum = 0.0
    if (d > 0) {
        val x1 = (-b + sqrt(d)) / (2 * a)  // 1'st root
        val x2 = (-b - sqrt(d)) / (2 * a)  // 2'st root
        println("First root = ${x1}\nSecond root = $x2")
        sum = (x1 + x2)
    }
    return sum
}