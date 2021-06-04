package id.co.moviejetpack.utils

import android.support.test.espresso.idling.CountingIdlingResource
import android.util.Log

object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    val idlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        idlingResource.increment()
        Log.d("idling", "increment: ")
    }

    fun decrement() {
        idlingResource.decrement()
        Log.d("idling", "decrement: ")
    }
}