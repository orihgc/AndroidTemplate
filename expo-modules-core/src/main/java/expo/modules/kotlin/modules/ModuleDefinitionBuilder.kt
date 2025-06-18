package expo.modules.kotlin.modules

import expo.modules.kotlin.events.EventsDefinition
import expo.modules.kotlin.objects.ObjectDefinitionBuilder

class ModuleDefinitionBuilder(module: Module? = null) : ObjectDefinitionBuilder() {

    internal var name: String? = null
    internal var eventsDefinition: EventsDefinition? = null


    fun Name(name: String) {
        this.name = name
    }

    fun Events(vararg events: String) {
        eventsDefinition = EventsDefinition(events)
    }


    fun buildModule(): ModuleDefinitionData {
        return ModuleDefinitionData()
    }
}