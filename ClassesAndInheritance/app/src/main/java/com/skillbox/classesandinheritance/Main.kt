package com.skillbox.classesandinheritance



fun main() {
    var zoo = Zoo().contains.toMutableList()

    repeat(8) {
        if (zoo.isEmpty()) return println("Zoo is empty")
        val temp = mutableListOf<Animal>()
        zoo.forEach { animal ->
            if(animal.isTooOld){
                println("Old $animal died...")
            } else temp += animal
            animal.doSomeActionOrReturnBornChild()?.let { temp += it }
        }
        zoo = temp
        }


        println()
        println(zoo.size)
//        println()
//        zoo.forEach { println(it.age) }

}
