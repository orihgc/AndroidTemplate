package expo.modules.kotlin

import android.content.Context
import expo.modules.core.errors.ModuleNotFoundException
import expo.modules.interfaces.filesystem.AppDirectoriesModuleInterface
import expo.modules.interfaces.imageloader.ImageLoaderInterface
import expo.modules.interfaces.permissions.Permissions
import java.io.File

class AppContext {

    val imageLoader: ImageLoaderInterface?=null

    val reactContext: Context?
        get() = null

    private val appDirectories: AppDirectoriesModuleInterface?=null

    val cacheDirectory: File
        get() = appDirectories?.cacheDirectory
            ?: throw ModuleNotFoundException("expo.modules.interfaces.filesystem.AppDirectories")

    val permissions: Permissions?=null
}