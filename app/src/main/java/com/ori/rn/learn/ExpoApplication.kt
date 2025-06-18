package com.ori.rn.learn

import android.app.Application
import com.bytedance.ai.bridge.ModuleRegistry
import expo.modules.camera.CameraViewModule

class ExpoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ModuleRegistry.registerModule(CameraViewModule())
    }
}