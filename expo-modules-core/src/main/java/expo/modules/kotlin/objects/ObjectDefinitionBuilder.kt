package expo.modules.kotlin.objects

import expo.modules.kotlin.Promise
import expo.modules.kotlin.functions.AsyncFunctionBuilder
import expo.modules.kotlin.functions.AsyncFunctionComponent
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent
import expo.modules.kotlin.functions.createAsyncFunctionComponent
import expo.modules.kotlin.types.enforceType
import expo.modules.kotlin.types.toArgsArray

open class ObjectDefinitionBuilder {

    var properties = mutableMapOf<String, PropertyComponentBuilder>()
    var asyncFunctions = mutableMapOf<String, AsyncFunctionComponent>()


    inline fun <reified T> Property(
        name: String,
        crossinline body: () -> T
    ): PropertyComponentBuilder {
        return PropertyComponentBuilder(name).also {
            it.get(body)
            properties[name] = it
        }
    }

    open fun Property(name: String): PropertyComponentBuilder {
        return PropertyComponentBuilder(name).also {
            properties[name] = it
        }
    }

    //region aync functions
    inline fun AsyncFunction(
        name: String,
        crossinline body: () -> Any?
    ): AsyncFunctionComponent {
        return createAsyncFunctionComponent(name, emptyArray()) { body() }.also {
            asyncFunctions[name] = it
        }
    }

    inline fun <reified R> AsyncFunction(
        name: String,
        crossinline body: () -> R
    ): AsyncFunctionComponent {
        return createAsyncFunctionComponent(name, emptyArray()) { body() }.also {
            asyncFunctions[name] = it
        }
    }

    inline fun <reified R, reified P0> AsyncFunction(
        name: String,
        crossinline body: (p0: P0) -> R
    ): AsyncFunctionComponent {
        // We can't split that function, because that introduces a ambiguity when creating DSL component without parameters.
        return if (P0::class.java == Promise::class.java) {
            AsyncFunctionWithPromiseComponent(name, arrayOf()) { _, promise -> body(promise as P0) }
        } else {
            createAsyncFunctionComponent(name, toArgsArray<P0>()) { (p0) ->
                enforceType<P0>(p0)
                body(p0)
            }
        }.also {
            asyncFunctions[name] = it
        }
    }

    inline fun <reified R, reified P0, reified P1> AsyncFunction(
        name: String,
        crossinline body: (p0: P0, p1: P1) -> R
    ): AsyncFunctionComponent {
        return createAsyncFunctionComponent(name, toArgsArray<P0, P1>()) { (p0, p1) ->
            enforceType<P0, P1>(p0, p1)
            body(p0, p1)
        }.also {
            asyncFunctions[name] = it
        }
    }

    @JvmName("AsyncFunctionWithPromise")
    inline fun <reified R, reified P0> AsyncFunction(
        name: String,
        crossinline body: (p0: P0, p1: Promise) -> R
    ): AsyncFunctionComponent {
        return AsyncFunctionWithPromiseComponent(name, toArgsArray<P0>()) { (p0), promise ->
            enforceType<P0>(p0)
            body(p0, promise)
        }.also {
            asyncFunctions[name] = it
        }
    }

    inline fun <reified R, reified P0, reified P1, reified P2> AsyncFunction(
        name: String,
        crossinline body: (p0: P0, p1: P1, p2: P2) -> R
    ): AsyncFunctionComponent {
        return createAsyncFunctionComponent(name, toArgsArray<P0, P1, P2>()) { (p0, p1, p2) ->
            enforceType<P0, P1, P2>(p0, p1, p2)
            body(p0, p1, p2)
        }.also {
            asyncFunctions[name] = it
        }
    }

    @JvmName("AsyncFunctionWithPromise")
    inline fun <reified R, reified P0, reified P1> AsyncFunction(
        name: String,
        crossinline body: (p0: P0, p1: P1, p2: Promise) -> R
    ): AsyncFunctionComponent {
        return AsyncFunctionWithPromiseComponent(name, toArgsArray<P0, P1>()) { (p0, p1), promise ->
            enforceType<P0, P1>(p0, p1)
            body(p0, p1, promise)
        }.also {
            asyncFunctions[name] = it
        }
    }

    inline fun <reified R, reified P0, reified P1, reified P2, reified P3> AsyncFunction(
        name: String,
        crossinline body: (p0: P0, p1: P1, p2: P2, p3: P3) -> R
    ): AsyncFunctionComponent {
        return createAsyncFunctionComponent(
            name,
            toArgsArray<P0, P1, P2, P3>()
        ) { (p0, p1, p2, p3) ->
            enforceType<P0, P1, P2, P3>(p0, p1, p2, p3)
            body(p0, p1, p2, p3)
        }.also {
            asyncFunctions[name] = it
        }
    }

    @JvmName("AsyncFunctionWithPromise")
    inline fun <reified R, reified P0, reified P1, reified P2> AsyncFunction(
        name: String,
        crossinline body: (p0: P0, p1: P1, p2: P2, p3: Promise) -> R
    ): AsyncFunctionComponent {
        return AsyncFunctionWithPromiseComponent(
            name,
            toArgsArray<P0, P1, P2>()
        ) { (p0, p1, p2), promise ->
            enforceType<P0, P1, P2>(p0, p1, p2)
            body(p0, p1, p2, promise)
        }.also {
            asyncFunctions[name] = it
        }
    }

    inline fun <reified R, reified P0, reified P1, reified P2, reified P3, reified P4> AsyncFunction(
        name: String,
        crossinline body: (p0: P0, p1: P1, p2: P2, p3: P3, p4: P4) -> R
    ): AsyncFunctionComponent {
        return createAsyncFunctionComponent(
            name,
            toArgsArray<P0, P1, P2, P3, P4>()
        ) { (p0, p1, p2, p3, p4) ->
            enforceType<P0, P1, P2, P3, P4>(p0, p1, p2, p3, p4)
            body(p0, p1, p2, p3, p4)
        }.also {
            asyncFunctions[name] = it
        }
    }

    @JvmName("AsyncFunctionWithPromise")
    inline fun <reified R, reified P0, reified P1, reified P2, reified P3> AsyncFunction(
        name: String,
        crossinline body: (p0: P0, p1: P1, p2: P2, p3: P3, p4: Promise) -> R
    ): AsyncFunctionComponent {
        return AsyncFunctionWithPromiseComponent(
            name,
            toArgsArray<P0, P1, P2, P3>()
        ) { (p0, p1, p2, p3), promise ->
            enforceType<P0, P1, P2, P3>(p0, p1, p2, p3)
            body(p0, p1, p2, p3, promise)
        }.also {
            asyncFunctions[name] = it
        }
    }

    inline fun <reified R, reified P0, reified P1, reified P2, reified P3, reified P4, reified P5> AsyncFunction(
        name: String,
        crossinline body: (p0: P0, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5) -> R
    ): AsyncFunctionComponent {
        return createAsyncFunctionComponent(
            name,
            toArgsArray<P0, P1, P2, P3, P4, P5>()
        ) { args ->  // 修改这里，不再使用解构声明
            enforceType<P0, P1, P2, P3, P4, P5>(
                args[0], args[1], args[2], args[3], args[4], args[5]
            )
            body(
                args[0] as P0,
                args[1] as P1,
                args[2] as P2,
                args[3] as P3,
                args[4] as P4,
                args[5] as P5
            )
        }.also {
            asyncFunctions[name] = it
        }
    }

    @JvmName("AsyncFunctionWithPromise")
    inline fun <reified R, reified P0, reified P1, reified P2, reified P3, reified P4> AsyncFunction(
        name: String,
        crossinline body: (p0: P0, p1: P1, p2: P2, p3: P3, p4: P4, p5: Promise) -> R
    ): AsyncFunctionComponent {
        return AsyncFunctionWithPromiseComponent(
            name,
            toArgsArray<P0, P1, P2, P3, P4>()
        ) { (p0, p1, p2, p3, p4), promise ->
            enforceType<P0, P1, P2, P3, P4>(p0, p1, p2, p3, p4)
            body(p0, p1, p2, p3, p4, promise)
        }.also {
            asyncFunctions[name] = it
        }
    }

    inline fun <reified R, reified P0, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6> AsyncFunction(
        name: String,
        crossinline body: (p0: P0, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6) -> R
    ): AsyncFunctionComponent {
        return createAsyncFunctionComponent(
            name,
            toArgsArray<P0, P1, P2, P3, P4, P5, P6>()
        ) { args ->  // 修改这里，不再使用解构声明
            enforceType<P0, P1, P2, P3, P4, P5, P6>(
                args[0], args[1], args[2], args[3], args[4], args[5], args[6]
            )
            body(
                args[0] as P0,
                args[1] as P1,
                args[2] as P2,
                args[3] as P3,
                args[4] as P4,
                args[5] as P5,
                args[6] as P6
            )
        }.also {
            asyncFunctions[name] = it
        }
    }

    @JvmName("AsyncFunctionWithPromise")
    inline fun <reified R, reified P0, reified P1, reified P2, reified P3, reified P4, reified P5> AsyncFunction(
        name: String,
        crossinline body: (p0: P0, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: Promise) -> R
    ): AsyncFunctionComponent {
        return AsyncFunctionWithPromiseComponent(
            name,
            toArgsArray<P0, P1, P2, P3, P4, P5>()
        ) { args, promise ->
            enforceType<P0, P1, P2, P3, P4, P5>(
                args[0],
                args[1],
                args[2],
                args[3],
                args[4],
                args[5]
            )
            body(
                args[0] as P0,
                args[1] as P1,
                args[2] as P2,
                args[3] as P3,
                args[4] as P4,
                args[5] as P5,
                promise
            )
        }.also {
            asyncFunctions[name] = it
        }
    }

    inline fun <reified R, reified P0, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7> AsyncFunction(
        name: String,
        crossinline body: (p0: P0, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7) -> R
    ): AsyncFunctionComponent {
        return createAsyncFunctionComponent(
            name,
            toArgsArray<P0, P1, P2, P3, P4, P5, P6, P7>()
        ) { args ->
            enforceType<P0, P1, P2, P3, P4, P5, P6, P7>(
                args[0],
                args[1],
                args[2],
                args[3],
                args[4],
                args[5],
                args[6],
                args[7]
            )
            body(
                args[0] as P0,
                args[1] as P1,
                args[2] as P2,
                args[3] as P3,
                args[4] as P4,
                args[5] as P5,
                args[6] as P6,
                args[7] as P7
            )
        }.also {
            asyncFunctions[name] = it
        }
    }

    @JvmName("AsyncFunctionWithPromise")
    inline fun <reified R, reified P0, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6> AsyncFunction(
        name: String,
        crossinline body: (p0: P0, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: Promise) -> R
    ): AsyncFunctionComponent {
        return AsyncFunctionWithPromiseComponent(
            name,
            toArgsArray<P0, P1, P2, P3, P4, P5, P6>()
        ) { args, promise ->
            enforceType<P0, P1, P2, P3, P4, P5, P6>(
                args[0],
                args[1],
                args[2],
                args[3],
                args[4],
                args[5],
                args[6]
            )
            body(
                args[0] as P0,
                args[1] as P1,
                args[2] as P2,
                args[3] as P3,
                args[4] as P4,
                args[5] as P5,
                args[6] as P6, promise
            )
        }.also {
            asyncFunctions[name] = it
        }
    }

    fun AsyncFunction(
        name: String
    ) = AsyncFunctionBuilder(name).also { asyncFunctionBuilders[name] = it }
    //endregion
}
