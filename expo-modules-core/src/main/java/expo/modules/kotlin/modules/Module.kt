package expo.modules.kotlin.modules

abstract class Module {

    abstract fun definition(): ModuleDefinitionData

    inline fun Module.ModuleDefinition(crossinline block: ModuleDefinitionBuilder.() -> Unit): ModuleDefinitionData {
        return ModuleDefinitionBuilder(this).also(block).buildModule()
    }

}