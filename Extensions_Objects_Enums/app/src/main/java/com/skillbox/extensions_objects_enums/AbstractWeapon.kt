package com.skillbox.extensions_objects_enums

import java.lang.Exception
import java.util.*

abstract class AbstractWeapon(
        val sizeOfMagazine: Int,
        val fireType: FireType,

        ) {

    var currentAmmo: List<Ammo> = emptyList()

    val hasAmmo: Boolean = currentAmmo.isNotEmpty()

    abstract fun createTheAmmo(): Ammo

    fun reload(): List<Ammo> {
        val reloadingAmmo: MutableList<Ammo> = mutableListOf()
        while(reloadingAmmo.size <= sizeOfMagazine) {
            reloadingAmmo.add(createTheAmmo())
        }
        currentAmmo = reloadingAmmo
        return currentAmmo
    }

    fun getAmmoToFire(): Ammo {
       return when(fireType) {
               FireType.Single -> createTheAmmo()
               FireType.Burst(3) -> {
                   if(hasAmmo) {
                       //не смог добавить сюда блок repeat()
                       createTheAmmo()
                       currentAmmo.toMutableList().removeAt(currentAmmo.lastIndex)
                       createTheAmmo()
                       currentAmmo.toMutableList().removeAt(currentAmmo.lastIndex)
                       createTheAmmo()
                       currentAmmo.toMutableList().removeAt(currentAmmo.lastIndex)
                   } else throw Exception("Magazine is empty")
               }
               else -> createTheAmmo()
           }
    }

}