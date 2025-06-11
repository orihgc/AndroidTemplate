package expo.modules.kotlin

/**
 * A context that holds the state of modules bounded to the JS runtime.
 */
class RuntimeContext(
    appContext: AppContext
) {

    private val appContextHolder = appContext.weak()

    val appContext: AppContext?
        get() = appContextHolder.get()
}
