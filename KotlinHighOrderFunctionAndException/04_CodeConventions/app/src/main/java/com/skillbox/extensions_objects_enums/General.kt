package com.skillbox.extensions_objects_enums

class General(
    maxHP: Int = 700,
    dodgeChance: Int = 40,
    accuracy: Int = 80,
    weapon: AbstractWeapon = Weapons.automaticWeapon
) : AbstractWarrior(maxHP, dodgeChance, accuracy, weapon) {

    override fun toString(): String {
        return "General"
    }
}
