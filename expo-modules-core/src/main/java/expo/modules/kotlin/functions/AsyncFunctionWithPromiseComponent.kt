package expo.modules.kotlin.functions

import expo.modules.kotlin.Promise
import expo.modules.kotlin.types.AnyType

class AsyncFunctionWithPromiseComponent constructor(
    name: String,
    desiredArgsTypes: Array<AnyType>,
    private val body: (args: Array<out Any?>, promise: Promise) -> Unit
) : AsyncFunctionComponent(name, desiredArgsTypes) {

    override fun callUserImplementation(args: Array<Any?>, promise: Promise) {
        body(convertArgs(args), promise)
    }
}