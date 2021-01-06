package com.skillbox.extensions_objects_enums

class Sniper(
    maxHP: Int = 100,
    dodgeChance: Int = 45,
    accuracy: Int = 90,
    weapon: AbstractWeapon = Weapons.sniperRifle
) : AbstractWarrior(maxHP, dodgeChance, accuracy, weapon) {

    override fun toString(): String {
        return "Sniper"
    }
}
