package com.skillbox.basemethodsandpropertydelegates
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class PetsOfPersons <T> (
        private var value: T
) : ReadOnlyProperty<Person, T> {

    override fun getValue(thisRef: Person, property: KProperty<*>): T {
        println(thisRef.pets)
        return value
    }

}