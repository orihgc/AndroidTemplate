package expo.modules.kotlin

import android.app.Activity

class AppContext {

    val hostingRuntimeContext: RuntimeContext? = null

    val throwingActivity: Activity
        get() {
            val current = activityProvider?.currentActivity
            return current ?: throw Exceptions.MissingActivity()
        }
}