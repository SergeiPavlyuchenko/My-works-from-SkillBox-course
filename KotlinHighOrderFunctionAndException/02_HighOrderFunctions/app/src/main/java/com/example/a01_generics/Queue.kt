package com.example.a01_generics

import java.util.function.BiPredicate

class Queue<T> {

    val elements: MutableSet<T> = mutableSetOf()

    fun enqueue(item: T) {
        elements.add(item)
    }

    fun dequeue(): T? {
        return elements.firstOrNull()
    }



}