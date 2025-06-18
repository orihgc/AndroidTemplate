package com.ori.rn.learn.bridge

import android.util.Log
import androidx.core.util.Consumer
import com.bytedance.ai.bridge.AIBridge
import com.bytedance.ai.bridge.ExpoMethodSeeker
import com.bytedance.ai.bridge.protocol.AbsAIBridgePort
import com.bytedance.ai.bridge.protocol.model.PostMessageWrapper
import com.bytedance.ai.bridge.protocol.model.ProtocolMessage
import com.bytedance.ai.bridge.protocol.model.ReceiveMessageWrapper
import com.bytedance.ai.bridge.protocol.model.TargetEntity
import com.bytedance.ai.bridge.utils.CacheHandle
import com.google.gson.JsonObject
import com.ori.rn.learn.utils.DefaultGson
import expo.modules.kotlin.AppContext

object SimulationJsRuntime {

    const val TAG = "AppletWidgetContainer"

    private val remoteMessageCache = CacheHandle<ProtocolMessage>()

    private val aiBridge = AIBridge()
    private var callbackId = 0

    init {
        aiBridge.addMethodSeeker(ExpoMethodSeeker)
        aiBridge.start(object : AbsAIBridgePort() {
            override fun realPostMessage(messageWrapper: PostMessageWrapper) {
                Log.i(TAG, "realPostMessage: ${DefaultGson.toJson(messageWrapper.message)}")
            }

            override fun realSetOnMessageCallback(consumer: Consumer<ReceiveMessageWrapper>) {
                remoteMessageCache.setConsumer {
                    consumer.accept(ReceiveMessageWrapper(it))
                }
            }
        })
        setupExpo()
    }

    private fun setupExpo(){
        AppContext.permissions = TestPermissionsImpl()
    }

    fun mockFeCallToNative(name: String, params: JsonObject? = null) {
        remoteMessageCache.offer(
            ProtocolMessage(
                name,
                params,
                ++callbackId,
                TargetEntity(scope = TargetEntity.Scope.System, target = TargetEntity.Target.Bridge),
                ProtocolMessage.Type.Call,
                System.currentTimeMillis()
            )
        )
    }


}