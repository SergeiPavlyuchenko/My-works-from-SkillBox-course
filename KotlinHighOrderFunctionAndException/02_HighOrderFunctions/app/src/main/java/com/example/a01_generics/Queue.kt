package com.example.a01_generics

class Queue<T : Number> {

    val elements: MutableList<T> = mutableListOf()

    fun enqueue(item: T) {
        elements.add(item)
    }

    @ExperimentalStdlibApi
    fun dequeue(): T? {
        return elements.removeFirstOrNull()
    }

    fun filter(callBack: (List<T>) -> List<T>): List<T> {
        return callBack(mutableListOf())
    }
}