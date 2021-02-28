package com.example.fragments_1

<<<<<<< HEAD
import android.os.Bundle
import androidx.fragment.app.Fragment

fun <T:Fragment> T.withArguments(action: Bundle.()-> Unit): T {
=======
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment

fun <T : Fragment> T.withArguments(action: Bundle.() -> Unit): T {
>>>>>>> origin
    return apply {
        val args = Bundle().apply(action)
        arguments = args
    }
<<<<<<< HEAD
}
=======
}

fun <T : Context> T.isTablet(): Boolean {
    return (resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) >=
            Configuration.SCREENLAYOUT_SIZE_LARGE

}


>>>>>>> origin
