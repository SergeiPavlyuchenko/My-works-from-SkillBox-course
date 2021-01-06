package com.skillbox.extensions_objects_enums


class NoAmmoException(override val message: String = "Magazine not have a ammo") : RuntimeException()