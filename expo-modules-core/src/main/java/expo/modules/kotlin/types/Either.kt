package expo.modules.kotlin.types

import expo.modules.kotlin.AppContext
import java.lang.ref.WeakReference
import kotlin.reflect.KClass
import kotlin.reflect.KType

sealed class DeferredValue
data class ConvertedValue(val convertedValue: Any) : DeferredValue()
data object IncompatibleValue : DeferredValue()

class UnconvertedValue(
    private val unconvertedValue: Any,
    private val typeConverter: TypeConverter<*>,
    context: AppContext?
) : DeferredValue() {
    private val contextHolder = WeakReference(context)

    private var convertedValue: Any? = null

    fun getConvertedValue(): Any {
        if (convertedValue == null) {
            convertedValue = typeConverter.convert(unconvertedValue, contextHolder.get())
        }

        return convertedValue!!
    }
}


open class Either<FirstType : Any, SecondType : Any>(
    private val bareValue: Any,
    private val deferredValue: MutableList<DeferredValue>,
    private val types: List<KType>
){

    internal fun `is`(index: Int): Boolean {
        return when (val value = deferredValue[index]) {
            is ConvertedValue -> true
            IncompatibleValue -> false
            is UnconvertedValue -> {
                try {
                    deferredValue[index] = ConvertedValue(value.getConvertedValue())
                    true
                } catch (e: Throwable) {
                    deferredValue[index] = IncompatibleValue
                    false
                }
            }
        }
    }
    internal fun get(index: Int): Any {
        return when (val value = deferredValue[index]) {
            is ConvertedValue -> value.convertedValue
            IncompatibleValue -> throw TypeCastException("Cannot cast '$bareValue' to '${types[index]}'")
            is UnconvertedValue -> {
                try {
                    val convertedValue = value.getConvertedValue()
                    deferredValue[index] = ConvertedValue(convertedValue)
                    convertedValue
                } catch (e: Throwable) {
                    deferredValue[index] = IncompatibleValue
//                    if (bareValue is Dynamic) {
//                        throw TypeCastException("Cannot cast '[$bareValue] ${bareValue.unwrap()}' to '${types[index]}' - ${e.message}")
//                    } else {
//                        throw TypeCastException("Cannot cast '$bareValue' to '${types[index]}' - ${e.message}")
//                    }
                }
            }
        }
    }
    @JvmName("isFirstType")
    fun `is`(@Suppress("UNUSED_PARAMETER") type: KClass<FirstType>): Boolean = `is`(0)

    @JvmName("isSecondType")
    fun `is`(@Suppress("UNUSED_PARAMETER") type: KClass<SecondType>): Boolean = `is`(1)

    @JvmName("getFirstType")
    fun get(@Suppress("UNUSED_PARAMETER") type: KClass<FirstType>): FirstType = get(0) as FirstType

    @JvmName("getSecondType")
    fun get(@Suppress("UNUSED_PARAMETER") type: KClass<SecondType>): SecondType = get(1) as SecondType

    fun first(): FirstType = get(0) as FirstType

    fun second(): SecondType = get(1) as SecondType
}