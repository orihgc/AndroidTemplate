package expo.modules.kotlin.functions

import expo.modules.kotlin.types.AnyType
import kotlinx.coroutines.CoroutineScope

class SuspendFunctionComponent(
    name: String,
    desiredArgsTypes: Array<AnyType>,
    private val body: suspend CoroutineScope.(args: Array<out Any?>) -> Any?
) : BaseAsyncFunctionComponent(name, desiredArgsTypes) {
}
