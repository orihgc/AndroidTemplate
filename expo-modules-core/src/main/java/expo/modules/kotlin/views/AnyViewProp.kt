package expo.modules.kotlin.views

import android.view.View
import expo.modules.kotlin.AppContext
import expo.modules.kotlin.types.AnyType

abstract class AnyViewProp(
  val name: String,
  internal val type: AnyType
) {
  abstract fun set(onView: View, appContext: AppContext? = null)

  abstract val isNullable: Boolean
}
