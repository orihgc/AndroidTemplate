package expo.modules.core.interfaces;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public interface ReactActivityLifecycleListener {
  default void onCreate(Activity activity, Bundle savedInstanceState) {}

  default void onResume(Activity activity) {}

  default void onPause(Activity activity) {}

  default void onUserLeaveHint(Activity activity) {}

  default void onDestroy(Activity activity) {}

  default boolean onNewIntent(Intent intent) {
    return false;
  }


  default boolean onBackPressed() {
    return false;
  }

  /**
   * This method is called when the {@link Activity#setContentView} method is invoked on an Activity.
   */
  default void onContentChanged(Activity activity) {}
}
