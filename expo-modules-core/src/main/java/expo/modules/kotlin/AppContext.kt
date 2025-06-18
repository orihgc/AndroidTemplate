package expo.modules.kotlin

import android.app.Activity
import android.content.Context
import expo.modules.core.errors.ModuleNotFoundException
import expo.modules.core.interfaces.ActivityProvider
import expo.modules.interfaces.filesystem.AppDirectoriesModuleInterface
import expo.modules.interfaces.imageloader.ImageLoaderInterface
import expo.modules.interfaces.permissions.Permissions
import expo.modules.kotlin.exception.Exceptions
import java.io.File

class AppContext {

    val hostingRuntimeContext = RuntimeContext(this)

    val imageLoader: ImageLoaderInterface?=null

    val reactContext: Context?
        get() = null

    private val appDirectories: AppDirectoriesModuleInterface?=null

    val cacheDirectory: File
        get() = appDirectories?.cacheDirectory
            ?: throw ModuleNotFoundException("expo.modules.interfaces.filesystem.AppDirectories")

    val permissions: Permissions?=null

    val activityProvider: ActivityProvider? = null

    val throwingActivity: Activity
        get() {
            val current = activityProvider?.currentActivity
            return current ?: throw Exceptions.MissingActivity()
        }

}