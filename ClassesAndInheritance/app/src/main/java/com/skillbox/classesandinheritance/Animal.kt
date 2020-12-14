package com.skillbox.classesandinheritance

import kotlin.random.Random

abstract class Animal(): AgedAnimal() {

    var energy: Int
        get() = field

    var weight: Int
        get() = field

    var age: Int
        get() = field
        private set


    var name: String
        get() = field

    init {
        energy = 10
        weight = 50
        age = 3
        name = "Lion"
    }

    val isTooOld: Boolean
        get() = age >= maxAge

    fun sleep() {
        if(isTooOld) return
        energy += 5
        age++
        println("$name sleeping")
    }

    fun eat() {
        if(isTooOld) return
        energy += 3
        weight++
        incrementAgeSometimes()
        println("$name eating")
    }

    fun incrementAgeSometimes() {
        if (Random.nextBoolean()) age++
    }


     open fun move() {
        when {
            isTooOld -> return
            energy < 5 -> return
            weight < 1 -> return
            else -> {
                energy -= 5
                weight--
                incrementAgeSometimes()
                println("$name moving")
            }
        }
    }

    open fun makeChild(): Animal {
        val child = object : Animal() { }
        child.energy = Random.nextInt(10) + 1
        child.weight = Random.nextInt(5) + 1
        println("${child.name} was born with ${child.energy} energy and ${child.weight} weight.")
        return child
    }

    tailrec fun toLiveFromBornToDie(animal: Animal) {
        if(animal.isTooOld) {
            println("Old ${animal.name} died :(\nWas born new child! Yohooo))")
            toLiveFromBornToDie(animal.makeChild())
        } else {
            if (Random.nextBoolean()) animal.eat()
            if (Random.nextBoolean()) animal.sleep()
            if (Random.nextBoolean()) animal.move()
            toLiveFromBornToDie(animal)
        }
    }



    open fun doSomeAction(): Animal? = when (Random.nextInt(4)) {
        0 -> null.also { eat() }
        1 -> null.also { sleep() }
        2 -> null.also { move() }
        3 -> makeChild()
        else -> throw IllegalStateException("Unknown case")
    }



}