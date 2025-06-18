package expo.modules.kotlin.modules

import android.os.Bundle
import expo.modules.kotlin.AppContext
import expo.modules.kotlin.providers.AppContextProvider
import expo.modules.kotlin.types.Enumerable

abstract class Module:AppContextProvider {

    override val appContext: AppContext = AppContext()

    abstract fun definition(): ModuleDefinitionData

    inline fun Module.ModuleDefinition(crossinline block: ModuleDefinitionBuilder.() -> Unit): ModuleDefinitionData {
        return ModuleDefinitionBuilder(this).also(block).buildModule()
    }

    fun sendEvent(name: String, body: Bundle? = Bundle.EMPTY) {

    }
    fun sendEvent(name: String, body: Map<String, Any?>) {}

    fun <T> sendEvent(enum: T, body: Bundle? = Bundle.EMPTY) where T : Enumerable, T : Enum<T> {}

    fun <T> sendEvent(enum: T, body: Map<String, Any?>? = null) where T : Enumerable, T : Enum<T> {}
}