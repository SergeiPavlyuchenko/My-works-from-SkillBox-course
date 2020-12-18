package com.skillbox.classesandinheritance

import kotlin.random.Random

class Dog(energy: Int, weight: Int, maxAge: Int = 30, name: String): Animal(energy, weight, name), Soundable {


    override val maxAge: Int = 40

    override fun move() {
        super.move()
        println("$name running")
    }

    override fun makeChild(): Dog {
        val child = Dog(
                energy = Random.nextInt(10) + 1,
                weight = Random.nextInt(5) + 1,
                name = name)
        println("${child.name} was born with ${child.energy} energy and ${child.weight} weight.")
        return child
    }

    override fun makeSound() {
        println("A dog says \"woof - woof\"")
    }

}