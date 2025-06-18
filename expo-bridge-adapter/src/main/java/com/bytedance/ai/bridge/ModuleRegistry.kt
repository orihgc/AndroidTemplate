package com.bytedance.ai.bridge

import expo.modules.kotlin.modules.Module

object ModuleRegistry {

    private val modulesMap = mutableMapOf<String, Module>()

    fun registerModule(module: Module) {
        modulesMap[module.definition().name] = module
    }

    fun getModule(name: String): Module? {
        return modulesMap[name]
    }

    fun hasModule(name: String): Boolean {
        return modulesMap.containsKey(name)
    }

    fun unRegisterModule(name: String) {
        modulesMap.remove(name)
    }
}