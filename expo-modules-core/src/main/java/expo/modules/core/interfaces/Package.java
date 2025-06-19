package expo.modules.core.interfaces;

import android.content.Context;

import java.util.Collections;
import java.util.List;

public interface Package {

  default List<? extends InternalModule> createInternalModules(Context context) {
    return Collections.emptyList();
  }

  default List<? extends SingletonModule> createSingletonModules(Context context) {
    return Collections.emptyList();
  }

}
