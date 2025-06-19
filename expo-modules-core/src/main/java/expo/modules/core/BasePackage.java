package expo.modules.core;

import android.content.Context;

import java.util.Collections;
import java.util.List;

import expo.modules.core.interfaces.ApplicationLifecycleListener;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.core.interfaces.ReactActivityLifecycleListener;
import expo.modules.core.interfaces.SingletonModule;

// This class should not be used. Implement expo.modules.core.interfaces.Package instead of extending this class
// Remove once no one extends it.
public class BasePackage {
    public List<InternalModule> createInternalModules(Context context) {
        return Collections.emptyList();
    }

    public List<SingletonModule> createSingletonModules(Context context) {
        return Collections.emptyList();
    }

    public List<ApplicationLifecycleListener> createApplicationLifecycleListeners(Context context) {
        return Collections.emptyList();
    }

    public List<ReactActivityLifecycleListener> createReactActivityLifecycleListeners(Context activityContext) {
        return Collections.emptyList();
    }
}
