package com.skillbox.classesandinheritance

class Zoo {

    private val animal = object : Animal(energy = 5, weight = 1, maxAge = 50, name = "Animal") { }
    private val bird = Bird(energy = 5, weight = 1, name = "Bird")
    private val fish = Fish(energy = 5, weight = 1,name = "Fish")
    private val dog = Dog(energy = 5, weight = 1,name = "Dog")


    val contains = listOf(
            bird, bird, bird, bird, bird,
            fish, fish, fish,
            dog, dog,
            animal, animal, animal, animal)


    /*fun create(): List<Animal> {
        val zoo = mutableListOf<Animal>()
        repeat(5) { zoo.add(bird) }
        repeat(3) { zoo.add(fish) }
        repeat(2) { zoo.add(dog) }
        repeat(4) { zoo.add(animal) }
        return zoo
    }*/

}