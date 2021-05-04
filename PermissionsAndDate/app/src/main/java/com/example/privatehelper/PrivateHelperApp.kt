package com.example.privatehelper

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import org.threeten.bp.Instant

class PrivateHelperApp: Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }

}