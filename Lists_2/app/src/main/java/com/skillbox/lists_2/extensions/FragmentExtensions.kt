package com.skillbox.lists_2

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun <T : FragmentManager> T.transaction(
    @IdRes container: Int,
    inputFragment: Fragment,
    addToBackStack: Boolean = true
) {
    if (addToBackStack) {
        beginTransaction()
            .replace(container, inputFragment)
            .addToBackStack(null)
            .commit()
    } else {
        beginTransaction()
            .replace(container, inputFragment)
            .commit()
    }


}