package com.example.privatehelper.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun <T : FragmentManager> T.transaction(
    @IdRes container: Int,
    inputFragment: Fragment,
    tag: String? = null,
    addToBackStack: Boolean = true
) {
    if (addToBackStack) {
        beginTransaction()
            .replace(container, inputFragment, tag)
            .addToBackStack(null)

            .commit()
    } else {
        beginTransaction()
            .replace(container, inputFragment)
            .commit()
    }
}