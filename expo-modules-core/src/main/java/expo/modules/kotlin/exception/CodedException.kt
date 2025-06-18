package expo.modules.kotlin.exception

import java.util.Locale
import kotlin.reflect.KType

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