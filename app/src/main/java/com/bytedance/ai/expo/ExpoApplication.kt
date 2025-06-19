package com.bytedance.ai.expo

import android.app.Application
import com.bytedance.ai.expo.utils.ActivityManager

class ExpoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ActivityManager.init(this)
    }
}