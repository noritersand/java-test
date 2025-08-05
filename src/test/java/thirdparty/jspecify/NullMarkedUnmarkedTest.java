package thirdparty.jspecify;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * <p>JSpecify에서 제공하는 어노테이션 테스트 슈트
 *
 * <p>JSpecify는 정적 분석 도구라서 IDE에서 경고만 발생시킬 뿐 런타임에 예외가 발생한다거나 하지는 않음
 */
class NullMarkedUnmarkedTest {

    /**
     * <p><code>@NullMarked</code> 어노테이션은 이 모듈/패키지/클래스/메서드/생성자함수 내 모든 요소는 기본적으로 null이 아님을 의미한다(<code>@NonNull</code>이 기본 적용).
     * 그리고 <code>@Nullable</code> 어노테이션으로 <code>null</code>을 허용할 부분만 지정한다.
     */
    @Test
    void testNullMarked() {
        // ⭐ 클래스에 @NullMarked를 적용했지만?

        // null을 반환해도 허용함
        String v1 = NullMarkedModel.emptyToNull("");
        assertThat(v1).isNull();

        // 파라미터가 null이어도 허용함
        String v2 = NullMarkedModel.nullToEmpty(null);

        // 파라미터의 null을 허용하지 않아서 경고 발생함
        String v3 = NullMarkedModel.notAllowNull(null);

        // null 반환을 허용하지 않아서 (해당 모델 코드를 가보면) 경고 발생함
        String v4 = NullMarkedModel.butReturnNull();
        assertThat(v4).isEqualTo(null);
    }

    /**
     * <p><code>@NullUnmarked</code> 어노테이션은 <code>null</code> 관련 어노테이션을 적용하지 않는다는 뜻이다.
     */
    @Test
    void testNullUnmarked() {
        // ⭐ @NullUnmarked는 어노테이션들을 아무 일도 하지 않게 만듦쓰

        String v1 = NullUnmarkedModel.emptyToNull("");
        assertThat(v1).isNull();

        String v2 = NullUnmarkedModel.nullToEmpty(null);

        String v3 = NullUnmarkedModel.notAllowNull(null);

        String v4 = NullMarkedModel.butReturnNull();
        assertThat(v4).isEqualTo(null);
    }
}
