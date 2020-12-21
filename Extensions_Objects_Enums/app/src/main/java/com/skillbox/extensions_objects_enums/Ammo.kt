package com.skillbox.extensions_objects_enums

import kotlin.random.Random

enum class Ammo(val damage: Int,
                val critChance: Int,
                val critRatio: Int) {

    FIRE(40,15,2),
    ELECTRIC(25, 60,3),
    WATER(30, 85,2);

    companion object {



        private fun getDamage(ammo: Ammo): Int {
            return if(Random.nextInt(100) + 1 <= ammo.critChance) {
                ammo.damage * ammo.critRatio
            } else ammo.damage
        }


        fun Int.toBoolean(ammo: Ammo): Boolean {
            return this == ammo.damage * ammo.critRatio
        }

    }


}