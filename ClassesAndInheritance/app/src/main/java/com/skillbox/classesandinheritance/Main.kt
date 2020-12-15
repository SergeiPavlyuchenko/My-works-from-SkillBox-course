package com.skillbox.classesandinheritance

import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
fun main() {
    val lion = object : Animal(0) {}
    val bird = Bird()
    val fish = Fish()
    val dog = Dog()
    var zoo = Zoo().create().toMutableList()


    repeat(10) {
        if (zoo.isEmpty()) return println("Zoo is empty")
        val temp = mutableListOf<Animal>()
        val died = mutableListOf<Animal>()
        zoo.forEach { animal ->
            if(animal.isTooOld){
                println("Old ${animal.name} died...")
                died += animal
            } else temp += animal
            animal.doSomeActionOrReturnBornChild()?.let { temp += it }
        }
        zoo = temp
        died.clear()
        }


        println()
        println(zoo.size)
        println()
        zoo.forEach { println(it.age) }
    }
