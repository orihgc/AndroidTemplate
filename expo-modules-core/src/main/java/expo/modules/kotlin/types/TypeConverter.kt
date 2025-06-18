package expo.modules.kotlin.types

import expo.modules.kotlin.AppContext

abstract class TypeConverter<Type : Any> {

    abstract fun convert(value: Any?, appContext: AppContext? = null): Type?

}