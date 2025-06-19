package com.bytedance.ai.bridge

import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinitionData

object ModuleRegistry {

    private val modulesMap = mutableMapOf<String, Module>()
    private val moduleDefinitions = mutableMapOf<String, ModuleDefinitionData>()

    fun registerModule(module: Module) {
        val definition = module.definition()
        modulesMap[definition.name] = module
        moduleDefinitions[definition.name] = definition
    }

    fun getModule(name: String): Module? {
        return modulesMap[name]
    }

    fun getModuleDefinition(name: String): ModuleDefinitionData? {
        return moduleDefinitions[name]
    }

    fun hasModule(name: String): Boolean {
        return modulesMap.containsKey(name)
    }

    fun unRegisterModule(name: String) {
        modulesMap.remove(name)
    }
}