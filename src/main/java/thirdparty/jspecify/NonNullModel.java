package thirdparty.jspecify;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

public class NonNullModel {

  public static String emptyToNull(String x) {
    return x.isEmpty() ? null : x;
  }

  public static String nullToEmpty(String x) {
    return x == null ? "" : x;
  }

  public static String notAllowNull(@NonNull String x) {
    return x;
  }

  public static @NonNull String butReturnNull() {
    return null;
  }

}
