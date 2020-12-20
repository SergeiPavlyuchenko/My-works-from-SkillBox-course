package com.skillbox.basemethodsandpropertydelegates

data class Animal(
    val energy: Int,
    val weight: Int ,
    val name: String
) {

    override fun toString(): String {
        return "Animal(energy = $energy, weight = $weight, name = '$name')"
    }
}