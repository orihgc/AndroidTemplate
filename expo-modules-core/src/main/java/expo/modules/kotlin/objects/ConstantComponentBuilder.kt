package expo.modules.kotlin.objects

class ConstantComponentBuilder {

    internal var properties = mutableMapOf<String, PropertyComponentBuilder>()


    open fun Property(name: String): PropertyComponentBuilder {
        return PropertyComponentBuilder(name).also {
            properties[name] = it
        }
    }
    inline fun <reified T> Property(name: String, crossinline body: () -> T): PropertyComponentBuilder {
        return PropertyComponentBuilder(name).also {
            properties[name] = it
        }
    }
}