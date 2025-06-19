package expo.modules.core

import expo.modules.core.interfaces.InternalModule
import expo.modules.core.interfaces.SingletonModule

class ModuleRegistry {

    private val mInternalModulesMap = mutableMapOf<Class<*>, InternalModule>()
    private val mSingletonModulesMap = mutableMapOf<String, SingletonModule>()


    fun <T> getModule(clazz: Class<T>): T? {
        return mInternalModulesMap[clazz] as? T
    }

    fun <T> getSingletonModule(name: String,singletonClass:Class<T>): T? {
        return mSingletonModulesMap[name] as? T
    }

}