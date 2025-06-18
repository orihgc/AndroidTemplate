package expo.modules.kotlin.functions

import expo.modules.kotlin.types.AnyType
import expo.modules.kotlin.types.ReturnType

class SyncFunctionComponent(
    name: String,
    argTypes: Array<AnyType>,
    private val returnType: ReturnType,
    private val body: (args: Array<out Any?>) -> Any?
) : AnyFunction(name, argTypes) {

    fun callUserImplementation(args: Array<Any?>): Any? {
        return body(convertArgs(args))
    }
}