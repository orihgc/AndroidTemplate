package com.bytedance.ai.bridge

import com.bytedance.ai.bridge.core.AIBridgeMethod
import com.bytedance.ai.bridge.core.IMethodSeeker

object ExpoMethodSeeker:IMethodSeeker {
    override fun seekMethod(methodName: String, aiContainerType: ContainerType): AIBridgeMethod? {
        return null
    }
}