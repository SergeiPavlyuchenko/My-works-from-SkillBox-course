package com.skillbox.extensions_objects_enums

abstract class AbstractWeapon(
        val sizeOfMagazine: Int,
        val fireType: FireType
        ) {

    var currentAmmo: MutableList<Ammo> = mutableListOf()

    val hasAmmo: Boolean = currentAmmo.isNotEmpty()

    abstract fun createTheAmmo(): Ammo

    fun reload() {
        while (currentAmmo.size < sizeOfMagazine) {
            currentAmmo.add(createTheAmmo())
        }
    }

    private fun addingAmmoFromCurrent(ammoToFire: MutableList<Ammo>, ammo: Ammo) {
        if (hasAmmo) {
            ammoToFire.add(ammo)
            currentAmmo.removeAt(currentAmmo.lastIndex)
        }
    }

    fun getAmmoToFire(): List<Ammo> {
        val ammoToFire = mutableListOf<Ammo>()
        val ammo = createTheAmmo()
        when (fireType) {
            FireType.Single -> addingAmmoFromCurrent(ammoToFire, ammo)
            FireType.Burst(2) -> {
                repeat(2) { addingAmmoFromCurrent(ammoToFire, ammo) }
            }
            FireType.Burst(sizeOfMagazine) -> {
                repeat(sizeOfMagazine) { addingAmmoFromCurrent(ammoToFire, ammo) }
            }
            else -> getAmmoToFire()
        }
        return ammoToFire
    }
}



    object Weapons {

        private fun createPistol(): AbstractWeapon {
            return object : AbstractWeapon(7,FireType.Single) {
                override fun createTheAmmo(): Ammo = createTheAmmo()
            }
        }

        private fun createShotgun(): AbstractWeapon {
            return object : AbstractWeapon(2,FireType.Burst(2)) {
                override fun createTheAmmo(): Ammo = createTheAmmo()
            }
        }

        private fun createSniperRifle(): AbstractWeapon {
            return object : AbstractWeapon(5, FireType.Single) {
                override fun createTheAmmo(): Ammo = createTheAmmo()
            }
        }

        private fun createAutomaticWeapon(): AbstractWeapon {
            return object : AbstractWeapon(30, FireType.Burst(30)) {
                override fun createTheAmmo(): Ammo = createTheAmmo()
            }
        }

        val pistol: AbstractWeapon = createPistol()
        val shotgun: AbstractWeapon = createShotgun()
        val sniperRifle: AbstractWeapon = createSniperRifle()
        val automaticWeapon: AbstractWeapon = createAutomaticWeapon()

    }





