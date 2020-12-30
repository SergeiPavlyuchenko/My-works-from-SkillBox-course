package com.example.a01_generics

class Queue<T> {

    val elements: MutableSet<T> = mutableSetOf()

    fun enqueue(item: T) {
        elements.add(item)
    }

    fun dequeue(): T? {
        return elements.firstOrNull()
    }

}