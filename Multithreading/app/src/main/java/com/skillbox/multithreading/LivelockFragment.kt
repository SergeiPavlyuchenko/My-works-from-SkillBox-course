package com.skillbox.multithreading

import android.util.Log
import androidx.fragment.app.Fragment
import java.util.concurrent.CountDownLatch
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

class LivelockFragment: Fragment() {

    private val lock1: Lock = ReentrantLock(true)
    private val lock2: Lock = ReentrantLock(true)

    //start work when 2 threads are started
    private val latch = CountDownLatch(2)

    override fun onResume() {
        super.onResume()
        Thread { operation1() }.start()
        Thread { operation2() }.start()
    }
    private fun operation1() {
        //Decrement count from each thread
        latch.countDown()
        //Waiting for count == 0. ie 2 threads are started
        latch.await()

        while (true) {
            if (lock1.tryLock()) {
                Log.d(
                    "Livelock",
                    "lock1 acquired, trying to acquire lock2. From thread = ${Thread.currentThread().name}"
                )
                if (lock2.tryLock()) {
                    Log.d("Livelock", "lock2 acquired. From thread = ${Thread.currentThread().name}")
                    Log.d("Livelock", "executing work. From thread = ${Thread.currentThread().name}")
                    lock2.unlock()
                    lock1.unlock()
                    break
                } else {
                    Log.d("Livelock", "cannot acquire lock2, releasing lock1. From thread = ${Thread.currentThread().name}")
                    lock1.unlock()
                    continue
                }
            } else {
                Log.d("Livelock", "cannot acquire lock1. From thread = ${Thread.currentThread().name}")
            }
        }
    }

    private fun operation2() {
        operation1()
    }

}