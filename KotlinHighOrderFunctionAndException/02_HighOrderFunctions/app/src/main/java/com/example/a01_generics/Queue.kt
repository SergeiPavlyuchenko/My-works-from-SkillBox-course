package com.example.a01_generics

class Queue<T : Number>(currentList: MutableList<T>) {

    var elements: MutableList<T> = currentList
    fun enqueue(item: T) {
        elements.add(item)
    }

    @ExperimentalStdlibApi
    fun dequeue(): T? {
        return elements.removeFirstOrNull()
    }

    fun filter(callBack: (MutableList<T>) -> MutableList<T>): Queue<T> {
        return Queue(callBack(elements))
    }

    override fun toString(): String {
        return "Queue(elements = $elements)"
    }


}