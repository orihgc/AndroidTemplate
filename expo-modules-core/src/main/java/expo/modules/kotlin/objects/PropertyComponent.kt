package expo.modules.kotlin.objects

import expo.modules.kotlin.functions.SyncFunctionComponent

class PropertyComponent(
    val name: String,
    val getter: SyncFunctionComponent? = null,
    val setter: SyncFunctionComponent? = null
)