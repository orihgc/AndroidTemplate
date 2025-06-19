package com.bytedance.ai.expo

import android.app.Application
import com.bytedance.ai.bridge.ModuleRegistry
import com.bytedance.ai.expo.utils.ActivityManager
import expo.modules.camera.CameraViewModule

class ExpoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ActivityManager.init(this)
        ModuleRegistry.registerModule(CameraViewModule())
    }
}