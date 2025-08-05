package thirdparty.jspecify;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NullMarkedModelTest {

    /**
     * JSpecify는 정적 분석 도구라서 경고만 발생할 뿐 런타임에 예외를 발생시키거나 하지는 않음
     */
    @Test
    void basicUsages() {
        String v = NullMarkedModel.notAllowNull(null);
        assertThat(v).isNull();

        String v2 = NullMarkedModel.butReturnNull();
        assertThat(v2).isNull();
    }
}
