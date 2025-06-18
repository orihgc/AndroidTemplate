package com.ori.rn.learn

import android.app.Application
import com.bytedance.ai.bridge.ModuleRegistry
import com.ori.rn.learn.utils.ActivityManager
import expo.modules.camera.CameraViewModule

class ExpoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ActivityManager.init(this)
        ModuleRegistry.registerModule(CameraViewModule())
    }
}