package com.skillbox.extensions_objects_enums

abstract class AbstractWeapon(
        val sizeOfMagazine: Int,
        val fireType: FireType
        ) {

    var currentAmmo: MutableList<Ammo> = mutableListOf()

    val hasAmmo: Boolean = currentAmmo.isNotEmpty()

    abstract fun createTheAmmo(): Ammo

    fun reload() {
        val temp = mutableListOf<Ammo>()
        while (currentAmmo.size < sizeOfMagazine) {
            temp.add(createTheAmmo())
        }
        currentAmmo = temp
    }



    fun getAmmoToFire(): List<Ammo> {
        return mutableListOf<Ammo>().apply {
            while (size < fireType.numbersOfAmmo && hasAmmo) {
                add(currentAmmo.removeFirst())
            }
        }
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





