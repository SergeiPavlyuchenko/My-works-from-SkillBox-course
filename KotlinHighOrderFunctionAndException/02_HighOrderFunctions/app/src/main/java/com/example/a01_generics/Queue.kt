package com.example.a01_generics

import java.util.function.BiPredicate

class Queue<T : Number>(currentList: List<T>) {

    private var elements: List<T> = currentList

    fun enqueue(item: T) {
        elements.toMutableList().add(item)
    }

    @ExperimentalStdlibApi
    fun dequeue(): T? {
        return elements.toMutableList().removeFirstOrNull()
    }

    fun filter(predicate: (T) -> Boolean): Queue<T> {
        val tempList: MutableList<T> = mutableListOf()
        elements.forEach { if(predicate(it)) tempList.add(it) }
        return Queue(tempList)
    }

    override fun toString(): String {
        return "Queue(elements = $elements)"
    }


}