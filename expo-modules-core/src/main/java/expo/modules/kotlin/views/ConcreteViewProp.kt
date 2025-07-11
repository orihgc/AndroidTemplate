package expo.modules.kotlin.views

import android.view.View
import expo.modules.kotlin.AppContext
import expo.modules.kotlin.exception.PropSetException
import expo.modules.kotlin.exception.exceptionDecorator
import expo.modules.kotlin.types.AnyType

class ConcreteViewProp<ViewType : View, PropType>(
  name: String,
  propType: AnyType,
  private val setter: (view: ViewType, prop: PropType) -> Unit
) : AnyViewProp(name, propType) {

  @Suppress("UNCHECKED_CAST")
  override fun set(onView: View, appContext: AppContext?) {
    exceptionDecorator({
      PropSetException(name, onView::class, it)
    }) {
      setter(onView as ViewType, type.convert(null, appContext) as PropType)
    }
  }

  override val isNullable: Boolean = propType.kType.isMarkedNullable
}
