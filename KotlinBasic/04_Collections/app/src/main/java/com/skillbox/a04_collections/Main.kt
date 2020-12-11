package com.skillbox.a04_collections

fun main() {

    print("Enter the number of phones: ")
    val number = readLine()?.toIntOrNull() ?: return print("This is not a number")
    val numberOfPhones = numberOfPhones(number)
    print("The phones which starting at \"+7\": ")
    println(numberOfPhones.filter { it.substring(0, 2) == "+7" }.joinToString(", "))
    println("The number of unique phones = ${numberOfPhones.toSet().size}")
    println("The sum of length of all phones = ${numberOfPhones.sumBy { it.length }}\n")

    val map = map(numberOfPhones.toSet())
    map.forEach { println("Человек: ${it.value}. Номер телефона: ${it.key}")}
}

fun numberOfPhones(n: Int): List<String> {
    println("Enter the $n phones: ")
    var currentNumber = 0
    val phonesList = mutableListOf<String>()
    while (currentNumber < n) {
        readLine()?.let { phonesList.add(it) }
        currentNumber++
    }
    return phonesList
}

fun map(set: Set<String>): MutableMap<String, String?> {
    val map = mutableMapOf<String, String?>()
    for (i in set) {
        print("Введите имя человека с номером телефона $i: ")
        val name = readLine().toString()
        map[i] = name
    }
    return map
}