package expo.modules.kotlin.types

abstract class TypeConverter<Type : Any> {

    abstract fun convert(value: Any?): Type?

}