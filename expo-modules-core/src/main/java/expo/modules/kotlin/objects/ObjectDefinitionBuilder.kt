package expo.modules.kotlin.objects

import expo.modules.kotlin.Promise
import expo.modules.kotlin.component6
import expo.modules.kotlin.component7
import expo.modules.kotlin.component8
import expo.modules.kotlin.events.EventsDefinition
import expo.modules.kotlin.functions.AsyncFunctionBuilder
import expo.modules.kotlin.functions.AsyncFunctionComponent
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent
import expo.modules.kotlin.functions.FunctionBuilder
import expo.modules.kotlin.functions.SyncFunctionComponent
import expo.modules.kotlin.functions.createAsyncFunctionComponent
import expo.modules.kotlin.types.TypeConverterProvider
import expo.modules.kotlin.types.enforceType
import expo.modules.kotlin.types.toArgsArray
import expo.modules.kotlin.types.toReturnType

open class ObjectDefinitionBuilder constructor(private val customConverter: TypeConverterProvider? = null) {

    var properties = mutableMapOf<String, PropertyComponentBuilder>()

    val converterProvider = customConverter

    internal var eventsDefinition: EventsDefinition? = null

    private var legacyConstantsProvider = { emptyMap<String, Any?>() }
    var constants = mutableMapOf<String, ConstantComponentBuilder>()

    var syncFunctions = mutableMapOf<String, SyncFunctionComponent>()

    var syncFunctionBuilder = mutableMapOf<String, FunctionBuilder>()

    var asyncFunctions = mutableMapOf<String, AsyncFunctionComponent>()
    var asyncFunctionBuilders = mutableMapOf<String, AsyncFunctionBuilder>()


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

    //region constants
    fun Constants(legacyConstantsProvider: () -> Map<String, Any?>) {
        this.legacyConstantsProvider = legacyConstantsProvider
    }

    /**
     * Definition of the module's constants to export.
     */
    fun Constants(vararg constants: Pair<String, Any?>) {
        legacyConstantsProvider = { constants.toMap() }
    }

    open fun Constant(name: String): ConstantComponentBuilder {
        return ConstantComponentBuilder(name).also {
            constants[name] = it
        }
    }

    /**
     * Creates the read-only constant whose getter doesn't take the caller as an argument.
     */
    inline fun <reified T> Constant(name: String, crossinline body: () -> T): ConstantComponentBuilder {
        return ConstantComponentBuilder(name).also {
            it.get(body)
            constants[name] = it
        }
    }
    //endregion

    //region events
    fun Events(vararg events: String) {
        eventsDefinition = EventsDefinition(events)
    }

    /**
     * Defines event names that this module can send to JavaScript.
     */
    @JvmName("EventsWithArray")
    fun Events(events: Array<String>) {
        eventsDefinition = EventsDefinition(events)
    }
    //endregion

    //region sync functions

    fun Function(
        name: String
    ) = FunctionBuilder(name).also { syncFunctionBuilder[name] = it }

    @JvmName("FunctionWithoutArgs")
    inline fun Function(
        name: String,
        crossinline body: () -> Any?
    ): SyncFunctionComponent {
        return SyncFunctionComponent(name, emptyArray(), toReturnType<Any?>()) { body() }.also {
            syncFunctions[name] = it
        }
    }

    inline fun <reified R> Function(
        name: String,
        crossinline body: () -> R
    ): SyncFunctionComponent {
        return SyncFunctionComponent(name, emptyArray(), toReturnType<R>()) { body() }.also {
            syncFunctions[name] = it
        }
    }

    inline fun <reified R, reified P0> Function(
        name: String,
        crossinline body: (p0: P0) -> R
    ): SyncFunctionComponent {
        return SyncFunctionComponent(name, toArgsArray<P0>(converterProvider = converterProvider), toReturnType<R>()) { (p0) ->
            enforceType<P0>(p0)
            body(p0)
        }.also {
            syncFunctions[name] = it
        }
    }

    inline fun <reified R, reified P0, reified P1> Function(
        name: String,
        crossinline body: (p0: P0, p1: P1) -> R
    ): SyncFunctionComponent {
        return SyncFunctionComponent(name, toArgsArray<P0, P1>(), toReturnType<R>()) { (p0, p1) ->
            enforceType<P0, P1>(p0, p1)
            body(p0, p1)
        }.also {
            syncFunctions[name] = it
        }
    }

    inline fun <reified R, reified P0, reified P1, reified P2> Function(
        name: String,
        crossinline body: (p0: P0, p1: P1, p2: P2) -> R
    ): SyncFunctionComponent {
        return SyncFunctionComponent(name, toArgsArray<P0, P1, P2>(), toReturnType<R>()) { (p0, p1, p2) ->
            enforceType<P0, P1, P2>(p0, p1, p2)
            body(p0, p1, p2)
        }.also {
            syncFunctions[name] = it
        }
    }

    inline fun <reified R, reified P0, reified P1, reified P2, reified P3> Function(
        name: String,
        crossinline body: (p0: P0, p1: P1, p2: P2, p3: P3) -> R
    ): SyncFunctionComponent {
        return SyncFunctionComponent(name, toArgsArray<P0, P1, P2, P3>(), toReturnType<R>()) { (p0, p1, p2, p3) ->
            enforceType<P0, P1, P2, P3>(p0, p1, p2, p3)
            body(p0, p1, p2, p3)
        }.also {
            syncFunctions[name] = it
        }
    }

    inline fun <reified R, reified P0, reified P1, reified P2, reified P3, reified P4> Function(
        name: String,
        crossinline body: (p0: P0, p1: P1, p2: P2, p3: P3, p4: P4) -> R
    ): SyncFunctionComponent {
        return SyncFunctionComponent(name, toArgsArray<P0, P1, P2, P3, P4>(), toReturnType<R>()) { (p0, p1, p2, p3, p4) ->
            enforceType<P0, P1, P2, P3, P4>(p0, p1, p2, p3, p4)
            body(p0, p1, p2, p3, p4)
        }.also {
            syncFunctions[name] = it
        }
    }

    inline fun <reified R, reified P0, reified P1, reified P2, reified P3, reified P4, reified P5> Function(
        name: String,
        crossinline body: (p0: P0, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5) -> R
    ): SyncFunctionComponent {
        return SyncFunctionComponent(name, toArgsArray<P0, P1, P2, P3, P4, P5>(), toReturnType<R>()) { (p0, p1, p2, p3, p4, p5) ->
            enforceType<P0, P1, P2, P3, P4, P5>(p0, p1, p2, p3, p4, p5)
            body(p0, p1, p2, p3, p4, p5)
        }.also {
            syncFunctions[name] = it
        }
    }

    inline fun <reified R, reified P0, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6> Function(
        name: String,
        crossinline body: (p0: P0, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6) -> R
    ): SyncFunctionComponent {
        return SyncFunctionComponent(name, toArgsArray<P0, P1, P2, P3, P4, P5, P6>(), toReturnType<R>()) { (p0, p1, p2, p3, p4, p5, p6) ->
            enforceType<P0, P1, P2, P3, P4, P5, P6>(p0, p1, p2, p3, p4, p5, p6)
            body(p0, p1, p2, p3, p4, p5, p6)
        }.also {
            syncFunctions[name] = it
        }
    }

    inline fun <reified R, reified P0, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7> Function(
        name: String,
        crossinline body: (p0: P0, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7) -> R
    ): SyncFunctionComponent {
        return SyncFunctionComponent(name, toArgsArray<P0, P1, P2, P3, P4, P5, P6, P7>(), toReturnType<R>()) { (p0, p1, p2, p3, p4, p5, p6, p7) ->
            enforceType<P0, P1, P2, P3, P4, P5, P6, P7>(p0, p1, p2, p3, p4, p5, p6, p7)
            body(p0, p1, p2, p3, p4, p5, p6, p7)
        }.also {
            syncFunctions[name] = it
        }
    }
    //endregion

    //region async functions
    @JvmName("AsyncFunctionWithoutArgs")
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
        return createAsyncFunctionComponent(name, toArgsArray<P0, P1, P2, P3>()) { (p0, p1, p2, p3) ->
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
        return AsyncFunctionWithPromiseComponent(name, toArgsArray<P0, P1, P2>()) { (p0, p1, p2), promise ->
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
        return createAsyncFunctionComponent(name, toArgsArray<P0, P1, P2, P3, P4>()) { (p0, p1, p2, p3, p4) ->
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
        return AsyncFunctionWithPromiseComponent(name, toArgsArray<P0, P1, P2, P3>()) { (p0, p1, p2, p3), promise ->
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
        return createAsyncFunctionComponent(name, toArgsArray<P0, P1, P2, P3, P4, P5>()) { (p0, p1, p2, p3, p4, p5) ->
            enforceType<P0, P1, P2, P3, P4, P5>(p0, p1, p2, p3, p4, p5)
            body(p0, p1, p2, p3, p4, p5)
        }.also {
            asyncFunctions[name] = it
        }
    }

    @JvmName("AsyncFunctionWithPromise")
    inline fun <reified R, reified P0, reified P1, reified P2, reified P3, reified P4> AsyncFunction(
        name: String,
        crossinline body: (p0: P0, p1: P1, p2: P2, p3: P3, p4: P4, p5: Promise) -> R
    ): AsyncFunctionComponent {
        return AsyncFunctionWithPromiseComponent(name, toArgsArray<P0, P1, P2, P3, P4>()) { (p0, p1, p2, p3, p4), promise ->
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
        return createAsyncFunctionComponent(name, toArgsArray<P0, P1, P2, P3, P4, P5, P6>()) { (p0, p1, p2, p3, p4, p5, p6) ->
            enforceType<P0, P1, P2, P3, P4, P5, P6>(p0, p1, p2, p3, p4, p5, p6)
            body(p0, p1, p2, p3, p4, p5, p6)
        }.also {
            asyncFunctions[name] = it
        }
    }

    @JvmName("AsyncFunctionWithPromise")
    inline fun <reified R, reified P0, reified P1, reified P2, reified P3, reified P4, reified P5> AsyncFunction(
        name: String,
        crossinline body: (p0: P0, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: Promise) -> R
    ): AsyncFunctionComponent {
        return AsyncFunctionWithPromiseComponent(name, toArgsArray<P0, P1, P2, P3, P4, P5>()) { (p0, p1, p2, p3, p4, p5), promise ->
            enforceType<P0, P1, P2, P3, P4, P5>(p0, p1, p2, p3, p4, p5)
            body(p0, p1, p2, p3, p4, p5, promise)
        }.also {
            asyncFunctions[name] = it
        }
    }

    inline fun <reified R, reified P0, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7> AsyncFunction(
        name: String,
        crossinline body: (p0: P0, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7) -> R
    ): AsyncFunctionComponent {
        return createAsyncFunctionComponent(name, toArgsArray<P0, P1, P2, P3, P4, P5, P6, P7>()) { (p0, p1, p2, p3, p4, p5, p6, p7) ->
            enforceType<P0, P1, P2, P3, P4, P5, P6, P7>(p0, p1, p2, p3, p4, p5, p6, p7)
            body(p0, p1, p2, p3, p4, p5, p6, p7)
        }.also {
            asyncFunctions[name] = it
        }
    }

    @JvmName("AsyncFunctionWithPromise")
    inline fun <reified R, reified P0, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6> AsyncFunction(
        name: String,
        crossinline body: (p0: P0, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: Promise) -> R
    ): AsyncFunctionComponent {
        return AsyncFunctionWithPromiseComponent(name, toArgsArray<P0, P1, P2, P3, P4, P5, P6>()) { (p0, p1, p2, p3, p4, p5, p6), promise ->
            enforceType<P0, P1, P2, P3, P4, P5, P6>(p0, p1, p2, p3, p4, p5, p6)
            body(p0, p1, p2, p3, p4, p5, p6, promise)
        }.also {
            asyncFunctions[name] = it
        }
    }

    fun AsyncFunction(
        name: String
    ) = AsyncFunctionBuilder(name).also { asyncFunctionBuilders[name] = it }
    //endregion

    fun buildObject(): ObjectDefinitionData {

        return ObjectDefinitionData(
            syncFunctions=syncFunctions + syncFunctionBuilder.mapValues { (_, value) -> value.build() },
            asyncFunctions = (asyncFunctions + asyncFunctionBuilders.mapValues { (_, value) -> value.build() }),
            properties = properties.mapValues { (_, value) -> value.build() },
            constants = constants.mapValues { (_, value) -> value.build() },
            eventsDefinition = eventsDefinition,
            legacyConstantsProvider = legacyConstantsProvider
        )
    }
}
