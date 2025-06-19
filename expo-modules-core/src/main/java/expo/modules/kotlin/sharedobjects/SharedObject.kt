package expo.modules.kotlin.sharedobjects

import expo.modules.kotlin.AppContext
import expo.modules.kotlin.RuntimeContext
import expo.modules.kotlin.weak

open class SharedObject(runtimeContext: RuntimeContext? = null) {

    var runtimeContextHolder = runtimeContext.weak()

    private val runtimeContext: RuntimeContext?
        get() = runtimeContextHolder.get()

    val appContext
        get() = runtimeContext?.appContext

    constructor(appContext: AppContext) : this(appContext.hostingRuntimeContext)

    open fun onStartListeningToEvent(eventName: String) = Unit

    open fun onStopListeningToEvent(eventName: String) = Unit

    open fun sharedObjectDidRelease() = deallocate()

    open fun deallocate() = Unit

    open fun getAdditionalMemoryPressure(): Int {
        return 0
    }
}