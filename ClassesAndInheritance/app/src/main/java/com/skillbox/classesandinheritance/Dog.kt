package com.skillbox.classesandinheritance

import kotlin.random.Random

class Dog(energy: Int = 5, weight: Int = 5, maxAge: Int = 40, name: String = "Bird"): Animal(energy, weight, maxAge, name), Soundable {



    override fun move() {
        name = "Dog"
        if (isTooOld || energy < 5 || weight < 1) return
        energy -= 5
        weight--
        incrementAgeSometimes()
        println("$name is running")
    }

    override fun makeChild(): Dog {
        val child = Dog()
        child.name = "Dog"
        child.energy = Random.nextInt(10) + 1
        child.weight = Random.nextInt(5) + 1
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