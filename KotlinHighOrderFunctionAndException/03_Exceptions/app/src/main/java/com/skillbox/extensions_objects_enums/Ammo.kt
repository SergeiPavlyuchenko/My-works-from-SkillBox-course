package com.skillbox.extensions_objects_enums

enum class Ammo(val damage: Int,
                val critChance: Int,
                val critRatio: Int) {

    FIRE(40,15,2),
    ELECTRIC(25, 60,3),
    WATER(30, 85,2),
    LONGSHOT(50,90,4);


    fun getTheDamage(): Int {
        return if(critChance.toBoolean()) damage * critRatio else damage
    }



}