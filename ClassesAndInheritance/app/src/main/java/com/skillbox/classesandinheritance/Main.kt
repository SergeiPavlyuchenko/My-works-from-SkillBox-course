package com.skillbox.classesandinheritance


fun main() {
    var zoo = Zoo().create().toMutableList()


    repeat(6) {
        if (zoo.isEmpty()) return println("Zoo is empty")
        val temp = mutableListOf<Animal>()
        zoo.forEach { animal ->
            if(animal.isTooOld){
                println("Old ${animal.name} died...")
            } else temp += animal
            animal.doSomeActionOrReturnBornChild()?.let { temp += it }
        }
        zoo = temp
        }


        println()
        println(zoo.size)
        println()
        zoo.forEach { println(it.age) }
    }
