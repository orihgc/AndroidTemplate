package expo.modules.kotlin.functions

import expo.modules.kotlin.types.AnyType
import kotlin.reflect.KType

abstract class AnyFunction(
    protected val name: String,
    protected val desiredArgsTypes: Array<AnyType>
) {

    var ownerType: KType? = null

    var canTakeOwner: Boolean = false


    fun convertArgs(args: Array<Any?>): Array<out Any?> {
        val finalArgs = Array<Any?>(desiredArgsTypes.size) { null }
        return finalArgs
    }
}

