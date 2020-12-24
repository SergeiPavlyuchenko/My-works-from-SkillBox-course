package com.skillbox.extensions_objects_enums

class Solder(maxHP: Int = 200,
              dodgeChance: Int = 10,
              accuracy: Int = 30,
              weapon: AbstractWeapon = Weapons.pistol
): AbstractWarrior(maxHP, dodgeChance, accuracy, weapon) {

    override val isKilled: Boolean
        get() = currentHP == 0

    override fun toString(): String {
        return "Solder"
    }


}