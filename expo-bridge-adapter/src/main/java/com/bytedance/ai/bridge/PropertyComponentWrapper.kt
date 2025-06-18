package com.bytedance.ai.bridge

import androidx.core.util.Consumer
import com.bytedance.ai.applet.base.gson.GsonUtil
import com.bytedance.ai.bridge.context.IAIBridgeContext
import com.bytedance.ai.bridge.core.AIBridgeMethod
import com.google.gson.JsonObject
import expo.modules.kotlin.objects.PropertyComponent

class PropertyComponentWrapper(private val propertyComponent: PropertyComponent) : AIBridgeMethod {
    override val name: String = propertyComponent.name

    override fun mustRunInMain(): Boolean = false

    override fun realHandle(
        bridgeContext: IAIBridgeContext,
        params: JsonObject?,
        resolve: Consumer<JsonObject>,
        reject: Consumer<AIBridgeMethod.Error>
    ) {
        val result =
            propertyComponent.getter?.callUserImplementation(convertJsonToArgs(params))
        if (result != null) {
            when (result) {
                is JsonObject -> {
                    resolve.accept(result)
                }
                is String -> {
                    resolve.accept(JsonObject().apply {
                        addProperty("value", result)
                    })
                }
                is Number -> {
                    resolve.accept(JsonObject().apply {
                        addProperty("value", result)
                    })
                }
                is Boolean -> {
                    resolve.accept(JsonObject().apply {
                        addProperty("value", result)
                    })
                }
                else -> {
                    resolve.accept(GsonUtil.toJsonObject(result) ?: JsonObject())
                    return
                }
            }
        } else {
            reject.accept(AIBridgeMethod.Error("Property $name is not defined"))
        }
    }
}