package expo.modules.kotlin.objects

import expo.modules.kotlin.functions.BaseAsyncFunctionComponent
import expo.modules.kotlin.functions.SyncFunctionComponent

class ObjectDefinitionData(
    val syncFunctions: Map<String, SyncFunctionComponent>,
    val asyncFunctions: Map<String, BaseAsyncFunctionComponent>,
) {

    val functions
        get() = ConcatIterator(syncFunctions.values.iterator(), asyncFunctions.values.iterator())
}

class ConcatIterator<T>(
    private val first: Iterator<T>,
    private val second: Iterator<T>
) : Iterator<T> {
    override fun hasNext(): Boolean = first.hasNext() || second.hasNext()

    override fun next(): T =
        if (first.hasNext()) {
            first.next()
        } else {
            second.next()
        }
}