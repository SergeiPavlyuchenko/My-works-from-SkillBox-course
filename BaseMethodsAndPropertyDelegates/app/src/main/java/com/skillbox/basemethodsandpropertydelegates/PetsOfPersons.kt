package com.skillbox.basemethodsandpropertydelegates
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class PetsOfPersons  (
        private var value: HashSet<Animal>
) : ReadOnlyProperty<Person, HashSet<Animal>> {

    override fun getValue(thisRef: Person, property: KProperty<*>): HashSet<Animal> {
        if(value.isNotEmpty()) println("${thisRef.name} pets: $value")
        return value
    }

}