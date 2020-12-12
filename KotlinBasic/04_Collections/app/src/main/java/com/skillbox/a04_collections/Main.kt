package com.skillbox.a04_collections

fun main() {

    print("Enter the number of phones: ")
    val number = inputInt()
    val numberOfPhones = getNumberOfPhones(number)
    print("The phones which starting at \"+7\": ")
    println(numberOfPhones.filter { it.startsWith("+7")  }.joinToString(", "))
    println("The number of unique phones = ${numberOfPhones.toSet().size}")
    println("The sum of length of all phones = ${numberOfPhones.sumBy { it.length }}\n")

    val map = convertSetOfPhonesToMap(numberOfPhones.toSet())
    map.forEach { println("Человек: ${it.value}. Номер телефона: ${it.key}")}
}

fun getNumberOfPhones(n: Int): List<String> {
    println("Enter the $n phones: ")
    val phones = mutableListOf<String>()
    repeat (n) {
        readLine()?.let { phones.add(it) }
    }
    return phones
}

fun convertSetOfPhonesToMap(setOfPhones: Set<String>): MutableMap<String, String?> {
    val map = mutableMapOf<String, String?>()
    setOfPhones.forEach {
        print("Введите имя человека с номером телефона $it: ")
        val name = readLine().toString()
        map[it] = name
    }
    return map
}


tailrec fun inputInt(): Int {
    return readLine()?.toIntOrNull() ?: inputInt()
}