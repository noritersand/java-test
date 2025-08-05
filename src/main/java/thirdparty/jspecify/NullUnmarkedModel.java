package thirdparty.jspecify;

import org.jspecify.annotations.NullUnmarked;
import org.jspecify.annotations.Nullable;

@NullUnmarked
public class NullUnmarkedModel {

  public static @Nullable String emptyToNull(String x) {
    return x.isEmpty() ? null : x;
  }

  public static String nullToEmpty(@Nullable String x) {
    return x == null ? "" : x;
  }

  public static String notAllowNull(String x) {
    return x;
  }

  public static String butReturnNull() {
    return null;
  }
}
