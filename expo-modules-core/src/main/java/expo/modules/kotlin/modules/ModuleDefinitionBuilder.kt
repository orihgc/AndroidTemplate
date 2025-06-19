package expo.modules.kotlin.modules

import android.app.Activity
import android.view.View
import expo.modules.kotlin.classcomponent.ClassComponentBuilder
import expo.modules.kotlin.events.OnActivityResultPayload
import expo.modules.kotlin.objects.ObjectDefinitionBuilder
import expo.modules.kotlin.sharedobjects.SharedObject
import expo.modules.kotlin.views.ViewDefinitionBuilder
import kotlin.reflect.KClass

class ModuleDefinitionBuilder(val module: Module? = null) : ObjectDefinitionBuilder() {

    internal var name: String? = null

    fun Name(name: String) {
        this.name = name
    }


    fun buildModule(): ModuleDefinitionData {
        val moduleName = name ?: module?.javaClass?.simpleName
        ?: throw IllegalArgumentException("Module name is required")
        return ModuleDefinitionData(moduleName, buildObject())
    }

    inline fun <reified T : View> View(viewClass: KClass<T>, body: ViewDefinitionBuilder<T>.() -> Unit) {}

    inline fun OnCreate(crossinline body: () -> Unit) {}
    inline fun OnDestroy(crossinline body: () -> Unit) {}
    inline fun OnActivityEntersForeground(crossinline body: () -> Unit) {}
    inline fun OnActivityEntersBackground(crossinline body: () -> Unit) {}
    inline fun OnActivityResult(crossinline body: (Activity, OnActivityResultPayload) -> Unit) {}

    inline fun Class(name: String, body: ClassComponentBuilder<Unit>.() -> Unit = {}) {}

    inline fun <reified SharedObjectType : SharedObject> Class(
        name: String,
        sharedObjectClass: KClass<SharedObjectType> = SharedObjectType::class,
        body: ClassComponentBuilder<SharedObjectType>.() -> Unit = {}
    ) {}

    inline fun <reified SharedObjectType : SharedObject> Class(
        sharedObjectClass: KClass<SharedObjectType> = SharedObjectType::class,
        body: ClassComponentBuilder<SharedObjectType>.() -> Unit = {}
    ) {}
}