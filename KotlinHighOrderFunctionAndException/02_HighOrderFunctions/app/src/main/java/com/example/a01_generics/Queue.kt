package com.example.a01_generics

class Queue<T : Number> {

    private val elements: MutableList<T> = mutableListOf()

    fun enqueue(item: T) {
        elements.add(item)
    }

    @ExperimentalStdlibApi
    fun dequeue(): T? {
        return elements.removeFirstOrNull()
    }


}