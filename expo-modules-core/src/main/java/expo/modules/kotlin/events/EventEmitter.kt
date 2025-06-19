package expo.modules.kotlin.events

import expo.modules.kotlin.records.Record

// We want to decorate a legacy event emitter interface to support advanced conversion between types in events.
// For instance, users will be able to create `Callback<Record>` that will be converted to the `WritableMap`.
interface EventEmitter : expo.modules.core.interfaces.services.EventEmitter {
  fun emit(eventName: String, eventBody: Record?)
  fun emit(eventName: String, eventBody: Map<*, *>?)
}
