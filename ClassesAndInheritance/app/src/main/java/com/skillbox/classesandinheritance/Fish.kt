package com.skillbox.classesandinheritance

import kotlin.random.Random

class Fish(energy: Int, weight: Int, maxAge: Int = 10, name: String): Animal(energy, weight, name) {


    override val maxAge: Int = 10

    override fun move() {
        super.move()
        println("$name swims")
    }

    override fun makeChild(): Fish {
        val child = Fish(
                energy = Random.nextInt(10) + 1,
                weight = Random.nextInt(5) + 1,
                name = name)
        println("${child.name} was born with ${child.energy} energy and ${child.weight} weight.")
        return child
    }

}