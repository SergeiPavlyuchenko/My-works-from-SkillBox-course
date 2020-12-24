package com.skillbox.extensions_objects_enums

class Captain(maxHP: Int = 500,
              dodgeChance: Int = 30,
              accuracy: Int = 50,
              weapon: AbstractWeapon = Weapons.shotgun
): AbstractWarrior(maxHP, dodgeChance, accuracy, weapon) {

    override val isKilled: Boolean
        get() = currentHP == 0

    override fun toString(): String {
        return "Captain"
    }


}