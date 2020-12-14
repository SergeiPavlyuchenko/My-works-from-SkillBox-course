package com.skillbox.classesandinheritance

import kotlin.random.Random

class Fish: Animal() {

    override val maxAge = 10

    override fun move() {
        name = "Fish"
        when {
            isTooOld -> return
            energy < 5 -> return
            weight < 1 -> return
            else -> {
                energy -= 5
                weight--
                incrementAgeSometimes()
                println("$name is swims")
            }
        }
    }

    override fun makeChild(): Fish {
        val child = Fish()
        child.name = "Fish"
        child.energy = Random.nextInt(10) + 1
        child.weight = Random.nextInt(5) + 1
        println("${child.name} was born with ${child.energy} energy and ${child.weight} weight.")
        return child
    }

}