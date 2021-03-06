package com.skillbox.extensions_objects_enums

sealed class FireType(val numbersOfAmmo: Int) {

    object Single: FireType(1)
    data class Burst(val stringOfBurst: Int): FireType(stringOfBurst)

}
