package com.skillbox.classesandinheritance

class Zoo {

    val animals = mutableListOf(
            Bird(energy = 5, weight = 5, name = "Bird"),
            Bird(energy = 5, weight = 5, name = "Bird"),
            Bird(energy = 5, weight = 5, name = "Bird"),
            Bird(energy = 5, weight = 5, name = "Bird"),
            Bird(energy = 5, weight = 5, name = "Bird"),
            Fish(energy = 5, weight = 5,name = "Fish"),
            Fish(energy = 5, weight = 5,name = "Fish"),
            Fish(energy = 5, weight = 5,name = "Fish"),
            Dog(energy = 5, weight = 5,name = "Dog"),
            Dog(energy = 5, weight = 5,name = "Dog"),
            object : Animal(energy = 5, weight = 5, name = "Animal") { },
            object : Animal(energy = 5, weight = 5, name = "Animal") { },
            object : Animal(energy = 5, weight = 5, name = "Animal") { },
            object : Animal(energy = 5, weight = 5, name = "Animal") { }
            )


}