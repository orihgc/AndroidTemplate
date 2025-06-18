package expo.modules.kotlin.viewevent

import android.view.View

fun interface ViewEventCallback<T> {
  operator fun invoke(arg: T)
}

open class ViewEvent<T>(
  private val name: String,
  private val view: View,
  private val coalescingKey: CoalescingKey<T>?
) : ViewEventCallback<T> {
  private var isValidated = false
  override fun invoke(arg: T) {

  }
}
