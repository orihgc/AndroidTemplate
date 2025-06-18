package expo.modules.kotlin.modules

import expo.modules.kotlin.objects.ObjectDefinitionData

class ModuleDefinitionData(
    val name: String,
    val objectDefinition: ObjectDefinitionData,
) {
    val syncFunctions = objectDefinition.syncFunctions
    val asyncFunctions = objectDefinition.asyncFunctions
    val functions = objectDefinition.functions
}