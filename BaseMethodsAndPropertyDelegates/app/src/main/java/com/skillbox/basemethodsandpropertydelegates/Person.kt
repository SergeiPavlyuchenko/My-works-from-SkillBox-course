package com.skillbox.basemethodsandpropertydelegates

import kotlin.random.Random

class Person(height: Int, weight: Int, name: String, pet: HashSet<Animal> = hashSetOf()) {

    val height: Int = height
        get() = field

    val weight: Int = weight
        get() = field

    val name: String = name
        get() = field

    val pets: HashSet<Animal>  by PetsOfPersons(pet)



    fun byPet() {
       // print("Enter a pet name: ")
        val pet = Animal(
            Random.nextInt(10) + 1,
            Random.nextInt(10) + 1,
            //readLine() ?: listOf("Lion", "Goose", "Herring", "Shepherd").random()
            listOf("Lion", "Goose", "Herring", "Shepherd").random()
        )
        pets.add(pet)
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

