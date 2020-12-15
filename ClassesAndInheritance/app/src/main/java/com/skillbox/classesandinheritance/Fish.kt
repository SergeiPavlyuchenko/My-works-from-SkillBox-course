package com.skillbox.classesandinheritance

import kotlin.random.Random

class Fish(energy: Int = 5, weight: Int = 5, maxAge: Int = 20, name: String = "Bird"): Animal(energy, weight, maxAge, name) {


    override fun move() {
        name = "Fish"
        if (isTooOld || energy < 5 || weight < 1) return
        energy -= 5
        weight--
        incrementAgeSometimes()
        println("$name is swims")
    }

    override fun makeChild(): Fish {
        val child = Fish()
        child.name = "Fish"
        child.energy = Random.nextInt(10) + 1
        child.weight = Random.nextInt(5) + 1
        println("${child.name} was born with ${child.energy} energy and ${child.weight} weight.")
        return child
    }

    override fun doSomeActionOrReturnBornChild(): Animal? = when (Random.nextInt(4)) {
        0 -> null.also { eat() }
        1 -> null.also { sleep() }
        2 -> null.also { move() }
        3 -> makeChild()
        else -> throw IllegalStateException("Unknown case")
    }

}