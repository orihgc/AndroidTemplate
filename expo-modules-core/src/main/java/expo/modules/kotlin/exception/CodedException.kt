package expo.modules.kotlin.exception

import java.util.Locale

class CodedException(message: String?, cause: Throwable? = null) : Exception(message, cause) {

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