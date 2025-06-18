package expo.modules.kotlin.functions

import expo.modules.kotlin.types.AnyType

abstract class AnyFunction(
    protected val name: String,
    protected val desiredArgsTypes: Array<AnyType>
) {

    fun convertArgs(args: Array<Any?>): Array<out Any?> {
        val finalArgs = Array<Any?>(desiredArgsTypes.size) { null }
        return finalArgs
    }
}

