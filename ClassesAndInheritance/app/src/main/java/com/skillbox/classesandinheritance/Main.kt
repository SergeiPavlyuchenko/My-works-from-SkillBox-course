package com.skillbox.classesandinheritance

import kotlin.random.Random

fun main() {
    val lion = object : Animal() { }
    val bird = Bird()
    val fish = Fish()
    val dog = Dog()
    val zoo = Zoo()
    val zooContains = zoo.zooContains()


//    lion.toLiveFromBornToDie(lion)


    repeat(300) {

        lion.doSomeAction()?.let { zoo.addingAChild(it, zooContains) }
        bird.doSomeAction()?.also { zoo.addingAChild(it, zooContains) }
        fish.doSomeAction()?.also { zoo.addingAChild(it, zooContains) }
        dog.doSomeAction()?.also { zoo.addingAChild(it, zooContains) }
    }
    println(zooContains.size)
}
