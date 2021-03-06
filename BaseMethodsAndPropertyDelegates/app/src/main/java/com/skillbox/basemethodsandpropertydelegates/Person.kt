package com.skillbox.basemethodsandpropertydelegates

import kotlin.random.Random

class Person(val height: Int, val weight: Int, val name: String) {


    val pets: HashSet<Animal>  by PetsOfPersons(hashSetOf())

    fun byPet() {

        val pet = Animal(
            Random.nextInt(10) + 1,
            Random.nextInt(10) + 1,
            getNameAnimal()
        )
        pets.add(pet)
    }

    private fun getNameAnimal(): String {
        print("Enter a pet name: ")
        val input = readLine()
        return if(input.isNullOrBlank()) {
            listOf("Lion", "Goose", "Herring", "Shepherd").random()
        } else input
    }
    

    override fun equals(other: Any?): Boolean {

        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Person

        if (height != other.height) return false
        if (weight != other.weight) return false
        if (name != other.name) return false
        if (pets != other.pets) return false

        return true
    }

    override fun hashCode(): Int {
        var result = height
        result = 31 * result + weight
        result = 31 * result + name.hashCode()
        result = 31 * result + pets.hashCode()
        return result
    }

    override fun toString(): String {
        return "Person(height = $height, weight = $weight, name = '$name', pets = $pets)"
    }

}

