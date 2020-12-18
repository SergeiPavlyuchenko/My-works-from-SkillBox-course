package com.skillbox.classesandinheritance

import kotlin.random.Random

abstract class Animal(
        energy: Int,
        weight: Int,
        val  name: String
) : AgedAnimal() {

    var energy: Int = energy
        private set

    var weight: Int = weight
        private set

    var age: Int = 0
        private set

    override val maxAge: Int
        get() = 50

    val isTooOld: Boolean
        get() = age >= maxAge

    fun sleep() {
        if (isTooOld) return
        energy += 5
        age++
        println("$name sleeping")
    }

    fun eat() {
        if (isTooOld) return
        energy += 3
        weight++
        incrementAgeSometimes()
        println("$name eating")
    }

    protected fun incrementAgeSometimes() {
        if (Random.nextBoolean()) age++
    }


    open fun move() {
        if (isTooOld || energy < 5 || weight < 1) return
        energy -= 5
        weight--
        incrementAgeSometimes()
        println("$name moving")
    }


    open fun makeChild(): Animal {
        val child = object : Animal(
                energy = Random.nextInt(10) + 1,
                weight = Random.nextInt(5) + 1,
                name) {
        }
        println("${child.name} was born with ${child.energy} energy and ${child.weight} weight.")
        return child
    }

   /* Напишите программу, которая создаёт животное. Животное должно есть, двигаться и спать до того
    момента, пока не станет старым. После этого рождается новое животное, и оно продолжает этот
    цикл. Убедитесь по логам, что цикл продолжается бесконечно и постоянно происходит рождение
    новых животных.*/

    tailrec fun toLiveFromBornToDie(animal: Animal) {
        if (animal.isTooOld) {
            println("Old ${animal.name} died :(\nWas born new child! Yohooo))")
            toLiveFromBornToDie(animal.makeChild())
        } else {
            if (Random.nextBoolean()) animal.eat()
            if (Random.nextBoolean()) animal.sleep()
            if (Random.nextBoolean()) animal.move()
            toLiveFromBornToDie(animal)
        }
    }

}
