package com.bytedance.ai.bridge

import com.google.gson.JsonObject

fun convertJsonToArgs(params: JsonObject?): Array<Any?> {
    return params?.entrySet()?.map { (_, value) ->
        when {
            value.isJsonPrimitive -> value.asJsonPrimitive.run {
                when {
                    isBoolean -> asBoolean
                    isNumber -> asNumber
                    isString -> asString
                    else -> null
                }
            }

            value.isJsonObject -> convertJsonToArgs(value.asJsonObject)
            value.isJsonArray -> value.asJsonArray.map { element ->
                if (element.isJsonObject) convertJsonToArgs(element.asJsonObject) else element
            }.toTypedArray()

            else -> null
        }
    }?.toTypedArray() ?: emptyArray()
}