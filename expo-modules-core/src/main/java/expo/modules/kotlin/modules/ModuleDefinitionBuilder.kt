package expo.modules.kotlin.modules

import expo.modules.kotlin.objects.ObjectDefinitionBuilder

class ModuleDefinitionBuilder(val module: Module? = null) : ObjectDefinitionBuilder() {

    internal var name: String? = null

    fun Name(name: String) {
        this.name = name
    }


    fun buildModule(): ModuleDefinitionData {
        return ModuleDefinitionData()
    }

    inline fun OnDestroy(crossinline body: () -> Unit) {

    }
}