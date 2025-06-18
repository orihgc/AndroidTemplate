package com.bytedance.ai.bridge

import androidx.core.util.Consumer
import com.bytedance.ai.applet.base.gson.GsonDefault
import com.bytedance.ai.bridge.context.IAIBridgeContext
import com.bytedance.ai.bridge.core.AIBridgeMethod
import com.google.gson.JsonObject
import expo.modules.kotlin.Promise
import expo.modules.kotlin.functions.AsyncFunctionComponent
import expo.modules.kotlin.functions.BaseAsyncFunctionComponent
import expo.modules.kotlin.functions.Queues

class BaseAsyncFunctionComponentWrapper(private val function: BaseAsyncFunctionComponent) :
    AIBridgeMethod {

    override val name: String = function.name


    override fun mustRunInMain(): Boolean {
        return function.queue == Queues.MAIN
    }

    override fun realHandle(
        bridgeContext: IAIBridgeContext,
        params: JsonObject?,
        resolve: Consumer<JsonObject>,
        reject: Consumer<AIBridgeMethod.Error>
    ) {
        if (function is AsyncFunctionComponent) {
            function.callUserImplementation(convertJsonToArgs(params), object : Promise {
                override fun resolve(value: Any?) {
                    val result =
                        value?.let { runCatching { GsonDefault.toJsonTree(it) }.getOrNull() }
                            ?: JsonObject().also {
                                resolve.accept(it)
                            }
                    result.let {
                        resolve.accept(it.asJsonObject)
                    }
                }

                override fun reject(code: String, message: String?, cause: Throwable?) {
                    reject.accept(AIBridgeMethod.Error(code.toInt(), message, null))
                }
            })
        } else {
            reject.accept(AIBridgeMethod.Error("not support"))
        }
    }
}

