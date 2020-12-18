package com.skillbox.classesandinheritance

import kotlin.random.Random

class Bird(energy: Int, weight: Int, name: String): Animal(energy, weight, name), Soundable {

    override val maxAge: Int = 15

    override fun move() {
        super.move()
        println("$name flying")
    }


    override fun makeChild(): Bird {
        val child = Bird(
                energy = Random.nextInt(10) + 1,
                weight = Random.nextInt(5) + 1,
                name = name)
        println("${child.name} was born with ${child.energy} energy and ${child.weight} weight.")
        return child
    }

    override fun makeSound() {
        println("A bird says \"chirp - chirp\"")
    }

}