package com.skillbox.multithreading

import android.app.Application
import com.skillbox.multithreading.threading.MovieRepository
import java.util.concurrent.*

class ThreadingApplication : Application() {
    private val NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors()
    private val workQueue: BlockingQueue<Runnable> =
        LinkedBlockingQueue<Runnable>()

    private val KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS
    private val threadPoolExecutor: ThreadPoolExecutor = ThreadPoolExecutor(
        NUMBER_OF_CORES,
        NUMBER_OF_CORES,
        KEEP_ALIVE_TIME,
        KEEP_ALIVE_TIME_UNIT,
        workQueue
    )

    fun getMovieRepository(): MovieRepository {
        return MovieRepository(threadPoolExecutor)
    }

    companion object {
        private const val KEEP_ALIVE_TIME = 1L

    }

}