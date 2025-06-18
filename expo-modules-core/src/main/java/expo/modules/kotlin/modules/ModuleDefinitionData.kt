package expo.modules.kotlin.modules

import expo.modules.kotlin.objects.ObjectDefinitionData

class ModuleDefinitionData(
    val name: String,
    val objectDefinition: ObjectDefinitionData,
) {
    val constantsProvider = objectDefinition.legacyConstantsProvider
    val syncFunctions = objectDefinition.syncFunctions
    val asyncFunctions = objectDefinition.asyncFunctions
    val eventsDefinition = objectDefinition.eventsDefinition
    val properties = objectDefinition.properties
    val constants = objectDefinition.constants
    val functions = objectDefinition.functions
}