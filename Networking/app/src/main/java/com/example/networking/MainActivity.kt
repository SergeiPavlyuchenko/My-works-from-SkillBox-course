package com.example.networking

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.litho.editor.flipper.LithoFlipperDescriptors
import com.facebook.soloader.SoLoader


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        SoLoader.init(this, false)
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            val client = AndroidFlipperClient.getInstance(this)
            val networkFlipperPlugin = NetworkFlipperPlugin()
            val descriptorMapping = DescriptorMapping.withDefaults()
            LithoFlipperDescriptors.add(descriptorMapping)
            client.addPlugin(InspectorFlipperPlugin(this, descriptorMapping))
            client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
            client.addPlugin(networkFlipperPlugin)
            client.addPlugin(InspectorFlipperPlugin(this, descriptorMapping))
            client.start()

        }
    }

}