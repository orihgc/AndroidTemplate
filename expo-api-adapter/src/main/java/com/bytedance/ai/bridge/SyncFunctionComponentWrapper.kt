package com.bytedance.ai.bridge

import androidx.core.util.Consumer
import com.bytedance.ai.bridge.context.IAIBridgeContext
import com.bytedance.ai.bridge.core.AIBridgeMethod
import com.google.gson.JsonObject
import expo.modules.kotlin.functions.SyncFunctionComponent

class SyncFunctionComponentWrapper(private val function: SyncFunctionComponent) : AIBridgeMethod {
    override val name: String = function.name

    override fun mustRunInMain(): Boolean = false

    override fun realHandle(
        bridgeContext: IAIBridgeContext,
        params: JsonObject?,
        resolve: Consumer<JsonObject>,
        reject: Consumer<AIBridgeMethod.Error>
    ) {
        TODO("Not yet implemented")
    }

}