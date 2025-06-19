package expo.modules.kotlin

import android.app.Activity
import android.content.Context
import expo.modules.core.ModuleRegistry
import expo.modules.core.errors.ModuleNotFoundException
import expo.modules.core.interfaces.ActivityProvider
import expo.modules.interfaces.filesystem.AppDirectoriesModuleInterface
import expo.modules.interfaces.filesystem.FilePermissionModuleInterface
import expo.modules.interfaces.imageloader.ImageLoaderInterface
import expo.modules.interfaces.permissions.Permissions
import expo.modules.kotlin.events.EventEmitter
import expo.modules.kotlin.exception.Exceptions
import expo.modules.kotlin.modules.Module
import java.io.File

object AppContext {

    val hostingRuntimeContext = RuntimeContext(this)

    val imageLoader: ImageLoaderInterface?=null

    val reactContext: Context?
        get() = null

    private val appDirectories: AppDirectoriesModuleInterface?=null

    val hasActiveReactInstance = true

    val cacheDirectory: File
        get() = appDirectories?.cacheDirectory
            ?: throw ModuleNotFoundException("expo.modules.interfaces.filesystem.AppDirectories")

    var permissions: Permissions? = null

    var activityProvider: ActivityProvider? = null

    val throwingActivity: Activity
        get() {
            val current = activityProvider?.currentActivity
            return current ?: throw Exceptions.MissingActivity()
        }

    val currentActivity: Activity?
        get() = activityProvider?.currentActivity

    val filePermission: FilePermissionModuleInterface? = null

    val legacyModuleRegistry: ModuleRegistry= ModuleRegistry()

    fun eventEmitter(module: Module): EventEmitter? {
        return null
    }

    inline fun <reified Module> legacyModule(): Module? {
        return try {
            legacyModuleRegistry?.getModule(Module::class.java)
        } catch (_: Exception) {
            null
        }
    }
}