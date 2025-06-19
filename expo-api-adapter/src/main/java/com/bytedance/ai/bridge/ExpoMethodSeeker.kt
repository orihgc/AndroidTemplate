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
        val moduleDefinitionData = ModuleRegistry.getModuleDefinition(moduleName) ?: return null
        moduleDefinitionData.syncFunctions.entries.forEach {
            if (it.key == realMethodName) {
                return SyncFunctionComponentWrapper(it.value)
            }
        }
        moduleDefinitionData.asyncFunctions.entries.forEach {
            if (it.key == realMethodName) {
                return BaseAsyncFunctionComponentWrapper(it.value)
            }
        }
        moduleDefinitionData.constants.entries.firstOrNull { it.key == realMethodName }?.let {
            return ConstantComponentWrapper(it.value)
        }
        moduleDefinitionData.properties.entries.firstOrNull { it.key == realMethodName }?.let {
            return PropertyComponentWrapper(it.value)
        }
        return null
    }
}
