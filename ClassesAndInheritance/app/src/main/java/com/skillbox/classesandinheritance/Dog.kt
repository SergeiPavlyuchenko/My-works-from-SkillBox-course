package com.skillbox.classesandinheritance

import kotlin.random.Random

class Dog(energy: Int, weight: Int, maxAge: Int = 30, name: String): Animal(energy, weight, maxAge, name), Soundable {



    override fun move() {
        super.move()
        println("$name running")
    }

    override fun makeChild(): Dog {
        val child = Dog(
                energy = Random.nextInt(10) + 1,
                weight = Random.nextInt(5) + 1,
                name = "Dog")
        println("${child.name} was born with ${child.energy} energy and ${child.weight} weight.")
        return child
    }

    override fun makeSound() {
        println("A dog says \"woof - woof\"")
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