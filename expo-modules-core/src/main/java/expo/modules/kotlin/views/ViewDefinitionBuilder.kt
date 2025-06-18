package expo.modules.kotlin.views

import android.view.View
import expo.modules.kotlin.types.toAnyType
import kotlin.reflect.KClass
import kotlin.reflect.KType

class ViewDefinitionBuilder<T : View>(
    internal val viewClass: KClass<T>,
    internal val viewType: KType
) {
    internal var name = viewClass.simpleName

    private var callbacksDefinition: CallbacksDefinition? = null
    var props = mutableMapOf<String, AnyViewProp>()



    fun Events(callbacks: Array<String>) {
        callbacksDefinition = CallbacksDefinition(callbacks)
    }

    inline fun <reified PropType> Prop(
        name: String,
        noinline body: (view: T, prop: PropType) -> Unit
    ) {
        props[name] = ConcreteViewProp(
            name,
            toAnyType<PropType>(),
            body
        )
    }

    inline fun OnViewDidUpdateProps(crossinline body: (view: T) -> Unit) {}
}