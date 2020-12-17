package com.skillbox.classesandinheritance

import kotlin.random.Random

abstract class Animal(
        energy: Int,
        weight: Int,
        maxAge: Int,
        name: String
) : AgedAnimal(maxAge) {

    var energy: Int = energy
        get
        private set(value) {
            field = value
        }

    var weight: Int = weight
        get
        private set

    var age: Int = 0
        get
        private set

    var name: String = name
        get


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
                this@Animal.maxAge,
                this@Animal.name) {}
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


        open fun doSomeActionOrReturnBornChild(): Animal? = when (Random.nextInt(4)) {
        0 -> null.also { eat() }
        1 -> null.also { sleep() }
        2 -> null.also { move() }
        3 -> makeChild()
        else -> throw IllegalStateException("Unknown case")
    }


}
