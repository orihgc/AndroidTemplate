package expo.modules.kotlin.objects

class ConstantComponent(
    /**
     * Name of the constant.
     */
    val name: String,

    /**
     * Synchronous function that is called when the constant is being accessed for the first time.
     */
    val getter: () -> Any?
) {
}