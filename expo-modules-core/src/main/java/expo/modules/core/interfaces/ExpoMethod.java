package expo.modules.core.interfaces;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

/**
 * Methods annotated with {@link ExpoMethod} will get exported to client code realm.
 */
@Retention(RUNTIME)
public @interface ExpoMethod {
}
