package com.bytedance.ai.bridge

import com.bytedance.ai.bridge.core.AIBridgeMethod
import com.bytedance.ai.bridge.core.IMethodSeeker
import expo.modules.kotlin.functions.BaseAsyncFunctionComponent
import expo.modules.kotlin.functions.SyncFunctionComponent

object ExpoMethodSeeker : IMethodSeeker {
    override fun seekMethod(methodName: String, aiContainerType: ContainerType): AIBridgeMethod? {
        val lastDotIndex = methodName.lastIndexOf('.')
        if (lastDotIndex == -1) return null
        val moduleName = methodName.substring(0, lastDotIndex)
        val realMethodName = methodName.substring(lastDotIndex + 1)
        val module = ModuleRegistry.getModule(moduleName) ?: return null
        module.definition().functions.forEach {
            if (it.name == realMethodName) {
                return when (it) {
                    is BaseAsyncFunctionComponent -> {
                        return BaseAsyncFunctionComponentWrapper(it)
                    }

                    is SyncFunctionComponent -> {
                        return SyncFunctionComponentWrapper(it)
                    }

                    else -> null
                }
            }
        }
        return null
    }
}
