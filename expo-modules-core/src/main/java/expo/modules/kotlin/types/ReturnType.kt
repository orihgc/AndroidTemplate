package expo.modules.kotlin.types

import kotlin.reflect.KClass

class ReturnType(private val kClass: KClass<*>) {
}

object ReturnTypeProvider {
    val types = mutableMapOf<KClass<*>, ReturnType>()

    inline fun <reified T> get(): ReturnType {
        return types[T::class] ?: ReturnType(T::class).also {
            types[T::class] = it
        }
    }
}

inline fun <reified T> toReturnType(): ReturnType {
    return ReturnTypeProvider.get<T>()
}