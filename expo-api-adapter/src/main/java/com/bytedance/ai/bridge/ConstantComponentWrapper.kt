package com.bytedance.ai.bridge

import androidx.core.util.Consumer
import com.bytedance.ai.applet.base.gson.GsonDefault
import com.bytedance.ai.applet.base.gson.GsonUtil
import com.bytedance.ai.bridge.context.IAIBridgeContext
import com.bytedance.ai.bridge.core.AIBridgeMethod
import com.google.gson.JsonObject
import expo.modules.kotlin.objects.ConstantComponent

class ConstantComponentWrapper(private val constantComponent: ConstantComponent) : AIBridgeMethod {
    override val name: String = constantComponent.name

    override fun mustRunInMain(): Boolean {
        return false
    }

    override fun realHandle(
        bridgeContext: IAIBridgeContext,
        params: JsonObject?,
        resolve: Consumer<JsonObject>,
        reject: Consumer<AIBridgeMethod.Error>
    ) {
        constantComponent.getter()?.let {
            GsonUtil.toJsonObject(it)?.let { json ->
                resolve.accept(json)
            }
        } ?: run {
            reject.accept(AIBridgeMethod.Error("Constant $name is not defined"))
        }
    }
}