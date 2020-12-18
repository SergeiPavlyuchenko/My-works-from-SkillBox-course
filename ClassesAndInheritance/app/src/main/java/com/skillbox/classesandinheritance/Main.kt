package com.skillbox.classesandinheritance

import kotlin.random.Random


fun main() {
    var zoo = Zoo().animals
    val tempZoo = ArrayList(zoo)

    repeat(3) {
        if (zoo.isEmpty()) return println("Zoo is empty")
        zoo.forEach {
            if (it.isTooOld) {
                println("Old $it died...")
            } else tempZoo.add(it)
            when (Random.nextInt(4)) {
                0 -> it.eat()
                1 -> it.sleep()
                2 -> it.move()
                3 -> tempZoo.add(it.makeChild())
                else -> throw IllegalStateException("Unknown case")
            }
            if (it is Soundable) it.makeSound()

        }
    }

    zoo = tempZoo


        println()
        println(zoo.size)
//        zoo.forEach { println(it.age) }

}
