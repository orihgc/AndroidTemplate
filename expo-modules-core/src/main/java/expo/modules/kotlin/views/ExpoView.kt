package expo.modules.kotlin.views

import android.content.Context
import android.graphics.Canvas
import android.widget.LinearLayout
import androidx.annotation.UiThread
import expo.modules.kotlin.AppContext

abstract class ExpoView(
    context: Context,
    val appContext: AppContext
) : LinearLayout(context) {

    open val shouldUseAndroidLayout: Boolean = false

    @UiThread
    fun measureAndLayout() {
        measure(
            MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        )
        layout(left, top, right, bottom)
    }

    override fun requestLayout() {
        super.requestLayout()
        if (shouldUseAndroidLayout) {
            // We need to force measure and layout, because React Native doesn't do it for us.
            post(Runnable { measureAndLayout() })
        }
    }

    open fun clipToPaddingBox(canvas: Canvas) {
        // When the border radius is set, we need to clip the content to the padding box.
        // This is because the border radius is applied to the background drawable, not the view itself.
        // It is the same behavior as in React Native.
        if (!clipToPadding) {
            return
        }
    }

    override fun dispatchDraw(canvas: Canvas) {
        clipToPaddingBox(canvas)
        super.dispatchDraw(canvas)
    }
}