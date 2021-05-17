package com.example.networking

import android.app.Application
import com.example.networking.network.Network
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.litho.editor.flipper.LithoFlipperDescriptors
import com.facebook.soloader.SoLoader

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            val descriptorMapping = DescriptorMapping.withDefaults()
            LithoFlipperDescriptors.add(descriptorMapping)
            val client = AndroidFlipperClient.getInstance(this).apply {
                addPlugin(InspectorFlipperPlugin(this@App, descriptorMapping))
                addPlugin(InspectorFlipperPlugin(this@App, DescriptorMapping.withDefaults()))
                addPlugin(Network.flipperNetworkPlugin)
                addPlugin(InspectorFlipperPlugin(this@App, descriptorMapping))
            }.start()
        }

    }
}