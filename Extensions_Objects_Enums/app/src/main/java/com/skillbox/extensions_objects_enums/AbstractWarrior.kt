package com.skillbox.extensions_objects_enums

abstract class AbstractWarrior(
        maxHP: Int,
        override val dodgeChance: Int,
        val accuracy: Int,
        val weapon: AbstractWeapon,
): Warrior {

    override var currentHP = maxHP

    override fun attack(warrior: Warrior) {
        var summaryDamage = 0
        if(!weapon.hasAmmo) {
            weapon.reload()
            return
        } else {
            weapon.getAmmoToFire()
                    .filter { accuracy > warrior.dodgeChance }
                    .forEach { summaryDamage += it.getTheDamage() }
        }
        warrior.takeDamage(summaryDamage)
    }

    override fun takeDamage(damage: Int) {
        currentHP - damage
    }


}