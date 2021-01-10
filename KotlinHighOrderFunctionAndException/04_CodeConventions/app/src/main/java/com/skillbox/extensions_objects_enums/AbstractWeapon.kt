package com.skillbox.extensions_objects_enums

abstract class AbstractWeapon(
    val sizeOfMagazine: Int,
    val fireType: FireType
) {

    var currentAmmo: MutableList<Ammo> = mutableListOf()

    val hasAmmo: Boolean
        get() = currentAmmo.isNotEmpty()

    abstract fun createTheAmmo(): Ammo

    fun reload() {
        val tempAmmo = MutableList(sizeOfMagazine) { createTheAmmo() }
        currentAmmo = tempAmmo
    }

    fun getAmmoToFire(): List<Ammo> {
        return mutableListOf<Ammo>().apply {
            while (size < fireType.numbersOfAmmo /*&& hasAmmo*/) {
                try {
                    if (!hasAmmo) throw NoAmmoException()
                    add(currentAmmo.removeFirst())
                } catch (t: NoAmmoException) {
//                    println(t.message)
//                    println("Reloading weapon")
                    reload()
                }
            }
        }
    }
}

object Weapons {

    private fun Ammo.createPistol(): AbstractWeapon {
        return object : AbstractWeapon(7, FireType.Single) {
            override fun createTheAmmo(): Ammo = this@createPistol
        }
    }

    private fun Ammo.createShotgun(): AbstractWeapon {
        return object : AbstractWeapon(2, FireType.Burst(2)) {
            override fun createTheAmmo(): Ammo = this@createShotgun
        }
    }

    private fun Ammo.createSniperRifle(): AbstractWeapon {
        return object : AbstractWeapon(5, FireType.Single) {
            override fun createTheAmmo(): Ammo = this@createSniperRifle
        }
    }

    private fun Ammo.createAutomaticWeapon(): AbstractWeapon {
        return object : AbstractWeapon(30, FireType.Burst(30)) {
            override fun createTheAmmo(): Ammo = this@createAutomaticWeapon
        }
    }

    val pistol: AbstractWeapon = Ammo.WATER.createPistol()
    val shotgun: AbstractWeapon = Ammo.FIRE.createShotgun()
    val sniperRifle: AbstractWeapon = Ammo.LONGSHOT.createSniperRifle()
    val automaticWeapon: AbstractWeapon = Ammo.ELECTRIC.createAutomaticWeapon()
}
