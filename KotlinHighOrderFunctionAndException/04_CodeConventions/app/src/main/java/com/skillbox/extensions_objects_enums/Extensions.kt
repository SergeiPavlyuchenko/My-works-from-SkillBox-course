package com.skillbox.extensions_objects_enums

import kotlin.random.Random

fun Int.toBoolean(): Boolean {
    return this > Random.nextInt(101)
}
