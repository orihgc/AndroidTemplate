package expo.modules.core.interfaces;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

@Retention(RUNTIME)
public @interface ExpoProp {
  String name();
}
