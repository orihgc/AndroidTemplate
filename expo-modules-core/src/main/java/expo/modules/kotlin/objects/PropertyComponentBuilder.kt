package expo.modules.kotlin.objects

import expo.modules.kotlin.functions.SyncFunctionComponent
import expo.modules.kotlin.types.toAnyType
import expo.modules.kotlin.types.toReturnType

open class PropertyComponentBuilder(val name: String) {
    var getter: SyncFunctionComponent? = null
    var setter: SyncFunctionComponent? = null

    inline fun <reified R> get(crossinline body: () -> R) = apply {
        getter = SyncFunctionComponent("get", emptyArray(), toReturnType<R>()) { body() }
    }

    inline fun <reified T> set(crossinline body: (newValue: T) -> Unit) = apply {
        setter = SyncFunctionComponent(
            "set",
            arrayOf(toAnyType<T>()),
            toReturnType<Unit>()
        ) { body(it[0] as T) }
    }


    fun build(): PropertyComponent {
        return PropertyComponent(name, getter, setter)
    }
}