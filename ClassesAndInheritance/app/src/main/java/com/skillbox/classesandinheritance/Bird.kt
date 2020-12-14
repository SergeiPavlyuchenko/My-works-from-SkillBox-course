package com.skillbox.classesandinheritance

import kotlin.random.Random

class Bird: Animal(), Soundable {

    override val maxAge = 30


    override fun move() {
        name = "Bird"
        when {
            isTooOld -> return
            energy < 5 -> return
            weight < 1 -> return
            else -> {
                energy -= 5
                weight--
                incrementAgeSometimes()
                println("$name is flying")
            }
        }
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

    override fun doSomeAction(): Animal? = when (Random.nextInt(5)) {
        0 -> null.also { eat() }
        1 -> null.also { sleep() }
        2 -> null.also { move() }
        3 -> null.also { makeSound() }
        4 -> makeChild()
        else -> throw IllegalStateException("Unknown case")
    }



}