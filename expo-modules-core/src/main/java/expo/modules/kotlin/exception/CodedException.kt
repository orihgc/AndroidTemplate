package expo.modules.kotlin.exception

import java.util.Locale
import kotlin.reflect.KClass
import kotlin.reflect.KType

@Suppress("NOTHING_TO_INLINE")
inline fun Throwable?.toCodedException() = when (this) {
    null -> UnexpectedException("Unknown error")
    is CodedException -> this
    is expo.modules.core.errors.CodedException -> CodedException(this.code, this.message, this.cause)
    else -> UnexpectedException(this)
}

open class CodedException(message: String?, cause: Throwable? = null) : Exception(message, cause) {

    private var providedCode: String? = null

    val code
        get() = providedCode ?: inferCode(javaClass)

    constructor(code: String, message: String?, cause: Throwable? = null) : this(
        message,
        cause
    ) {
        this.providedCode = code
    }

    companion object {
        internal fun inferCode(clazz: Class<*>): String {
            val name =
                requireNotNull(clazz.simpleName) { "Cannot infer code name from class name. We don't support anonymous classes." }

            return "ERR_" + name
                .replace("(Exception)$".toRegex(), "")
                .replace("(.)([A-Z])".toRegex(), "$1_$2")
                .uppercase(Locale.ROOT)
        }
    }
}

internal class MissingTypeConverter(
    forType: KType
) : CodedException(
    message = "Cannot find type converter for '$forType'. Make sure the class implements `expo.modules.kotlin.records.Record` (i.e. `class MyObj : Record`)."
)

class UnexpectedException(
    message: String?,
    cause: Throwable? = null
) : CodedException(message = message, cause) {
    constructor(throwable: Throwable) : this(throwable.toString(), throwable)
    constructor(message: String) : this(message, null)
}

open class DecoratedException(
    message: String,
    cause: CodedException
) : CodedException(
    cause.code,
    message = "$message${System.lineSeparator()}→ Caused by: ${cause.localizedMessage ?: cause}",
    cause
)

internal class PropSetException(
    propName: String,
    viewType: KClass<*>,
    cause: CodedException
) : DecoratedException(
    message = "Cannot set prop '$propName' on view '$viewType'",
    cause
)