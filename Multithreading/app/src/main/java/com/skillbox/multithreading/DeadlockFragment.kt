package com.skillbox.multithreading

import android.util.Log
import androidx.fragment.app.Fragment

class DeadlockFragment: Fragment() {

    private var i = 0
    private val lock1 = Any()
    private val lock2 = Any()

    override fun onResume() {
        super.onResume()

        val friend1 = Person("Вася")
        val friend2 = Person("Петя")

        val thread1 = Thread {
            Log.d("Deadlock", "Start1 on the ${Thread.currentThread().name}, i: $i")
            (0..1_000_000).forEach {
                synchronized(lock1) {
                    synchronized(lock1) {
                        i++
                        if (i >= 999998 && i == 1_000_000) Log.d("Deadlock", "middle1 on the ${Thread.currentThread().name}, i: $i")
                    }
                }
            }
            Log.d("Deadlock", "End1 on the ${Thread.currentThread().name}, i: $i")
        }

        val thread2 = Thread {
            Log.d("Deadlock", "Start2 on the ${Thread.currentThread().name}, i: $i")
            (0..1_000_000).forEach {
                synchronized(lock2) {
                    synchronized(lock2) {
                        i++
                        if (i >= 999998 && i == 1_000_000) Log.d("Deadlock", "middle2 on the ${Thread.currentThread().name}, i: $i")
                    }
                }
            }

            Log.d("Deadlock", "End2 on the ${Thread.currentThread().name}, i: $i")
        }

        thread1.start()
        thread2.start()
    }


    data class Person(
        val name: String
    ) {

        fun throwBallTo(friend: Person) {
            synchronized(this) {
                Log.d(
                    "Person",
                    "$name бросает мяч ${friend.name} на потоке ${Thread.currentThread().name}"
                )
                Thread.sleep(500)
            }
            friend.throwBallTo(this)
        }

    }
}