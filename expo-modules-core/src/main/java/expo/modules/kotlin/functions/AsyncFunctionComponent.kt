package expo.modules.kotlin.functions

import expo.modules.kotlin.Promise
import expo.modules.kotlin.types.AnyType

abstract class AsyncFunctionComponent(name: String, desiredArgsTypes: Array<AnyType>) :
    BaseAsyncFunctionComponent(name, desiredArgsTypes) {

    internal abstract fun callUserImplementation(args: Array<Any?>, promise: Promise)
}