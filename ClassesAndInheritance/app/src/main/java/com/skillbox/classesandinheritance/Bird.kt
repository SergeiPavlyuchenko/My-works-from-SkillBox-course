package com.skillbox.classesandinheritance

import kotlin.random.Random

class Bird(energy: Int = 5, weight: Int = 5, maxAge: Int = 15, name: String = "Bird"): Animal(energy, weight, maxAge, name), Soundable {





    override fun move() {
        name = "Bird"
        if (isTooOld || energy < 5 || weight < 1) return
        energy -= 5
        weight--
        incrementAgeSometimes()
        println("$name is flying")
    }


    override fun makeChild(): Bird {
        val child = Bird()
        child.name = "Bird"
        child.energy = Random.nextInt(10) + 1
        child.weight = Random.nextInt(5) + 1
        println("${child.name} was born with ${child.energy} energy and ${child.weight} weight.")
        return child
    }

    override fun makeSound() {
        println("A bird says \"chirp - chirp\"")
    }

    override fun doSomeActionOrReturnBornChild(): Animal? = when (Random.nextInt(5)) {
        0 -> null.also { eat() }
        1 -> null.also { sleep() }
        2 -> null.also { move() }
        3 -> null.also { makeSound() }
        4 -> makeChild()
        else -> throw IllegalStateException("Unknown case")
    }



}