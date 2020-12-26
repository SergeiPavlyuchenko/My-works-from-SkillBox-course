package com.skillbox.extensions_objects_enums

interface Warrior {

    val currentHP: Int

    val isKilled: Boolean

    val dodgeChance: Int

    fun attack(warrior: Warrior)

    fun takeDamage(damage: Int): Int

}