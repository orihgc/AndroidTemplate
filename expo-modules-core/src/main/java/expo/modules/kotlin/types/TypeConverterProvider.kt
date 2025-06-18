package expo.modules.kotlin.types

import kotlin.reflect.KType

interface TypeConverterProvider {
    fun obtainTypeConverter(type: KType): TypeConverter<*>
}
