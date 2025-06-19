package expo.modules.kotlin.functions

import expo.modules.kotlin.types.AnyType
import kotlin.reflect.KType

abstract class AnyFunction(
    val name: String,
    private val desiredArgsTypes: Array<AnyType>
) {

    var ownerType: KType? = null

    var canTakeOwner: Boolean = false


    fun convertArgs(args: Array<Any?>): Array<out Any?> {
//        val finalArgs = Array<Any?>(desiredArgsTypes.size) { null }
//        val argIterator = args.iterator()
//        for (index in args.indices) {
//            val desiredType = desiredArgsTypes[index]
//            argIterator.next().apply {
//                desiredType.convert(this)?.let {
//                    finalArgs[index] = it
//                }
//            }
//        }
//        return finalArgs
        return args
    }
}

