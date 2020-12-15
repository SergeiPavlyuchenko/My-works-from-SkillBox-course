package com.skillbox.classesandinheritance

class Zoo {

    val bird = Bird()
    val fish = Fish()
    val dog = Dog()
    val animal = object : Animal() { }



    fun create(): List<Animal> {
        val zoo = mutableListOf<Animal>()
        repeat(5) { zoo.add(bird) }
        repeat(3) { zoo.add(fish) }
        repeat(2) { zoo.add(dog) }
        repeat(4) { zoo.add(animal) }
        return zoo
    }

}