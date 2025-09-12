package thirdparty.jspecify;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <p>JSpecify에서 제공하는 어노테이션 테스트 슈트
 *
 * <p>JSpecify는 정적 분석 도구라서 IDE에서 경고만 발생시킬 뿐 런타임에 예외가 발생한다거나 하지는 않음
 */
class JSpecifyTest {

    /**
     * <p><code>@NullMarked</code> 어노테이션은 이 모듈/패키지/클래스/메서드/생성자함수 내 모든 요소는 기본적으로 null이 아님을 의미한다(<code>@NonNull</code>이 기본 적용).
     *
     * <p>그리고 <code>@Nullable</code> 어노테이션으로 <code>null</code>을 허용할 부분만 지정한다.
     */
    @Test
    void testNullMarked() {
        // ⭐ 클래스에 @NullMarked를 적용했지만?

        // null을 반환해도 허용함
        String v1 = NullMarkedModel.emptyToNull("");
        assertThat(v1).isNull();

        // 파라미터가 null이어도 허용함
        String v2 = NullMarkedModel.nullToEmpty(null);
        assertThat(v2).isEqualTo("");

        // 파라미터의 null을 허용하지 않아서 경고 발생함
        String v3 = NullMarkedModel.notAllowNull(null);
        assertThat(v3).isNull();

        // null 반환을 허용하지 않아서 (해당 모델 코드를 가보면) 경고 발생함
        String v4 = NullMarkedModel.butReturnNull();
        assertThat(v4).isEqualTo(null);
    }

    /**
     * <p><code>@NullUnmarked</code> 어노테이션은 <code>null</code> 관련 어노테이션을 적용하지 않는다는 뜻이다.
     *
     * <p>사실상 아무 어노테이션 없이 선언한 것과 같다.
     *
     * <p>상위 패키지나 모듈, 클래스 등에서 <code>@NullMarked</code>가 붙어있을 때 하위의 특정 범위에서 "여긴 null 관련 어노테이션 적용 안할거야"르 하고 싶을 때 쓴다.
     */
    @Test
    void testNullUnmarked() {
        // ⭐ @NullUnmarked는 어노테이션들을 아무 일도 하지 않게 만듦쓰

        String v1 = NullUnmarkedModel.emptyToNull("");
        assertThat(v1).isNull();

        String v2 = NullUnmarkedModel.nullToEmpty(null);
        assertThat(v2).isEqualTo("");

        String v3 = NullUnmarkedModel.notAllowNull(null);
        assertThat(v3).isNull();

        String v4 = NullUnmarkedModel.butReturnNull();
        assertThat(v4).isEqualTo(null);
    }

    /**
     * 아무 어노테이션이 없을 때
     */
    @Test
    void testNoAnnotated() {
        String v1 = NoAnnotatedModel.emptyToNull("");
        assertThat(v1).isNull();

        String v2 = NoAnnotatedModel.nullToEmpty(null);
        assertThat(v2).isEqualTo("");

        String v3 = NoAnnotatedModel.notAllowNull(null);
        assertThat(v3).isNull();

        String v4 = NoAnnotatedModel.butReturnNull();
        assertThat(v4).isEqualTo(null);
    }

    /**
     * <p><code>@NonNull</code>은 파라미터나 반환 타입에 null 미허용을 각각 지정할 때 쓴다.</p>
     * <p>@NullMarked만 있는 클래스는 모든 메서드의 파라미터와 반환 타입에 @NonNull이 있는 것과 같다.</p>
     * <p>@NullMarked를 쓰기 전에 임시로 적용하는 어노테이션인 셈</p>
     */
    @Test
    void testNonNull() {
        String v1 = NonNullModel.emptyToNull("");
        assertThat(v1).isNull();

        String v2 = NonNullModel.nullToEmpty(null);
        assertThat(v2).isEqualTo("");

        String v3 = NonNullModel.notAllowNull(null);
        assertThat(v3).isNull();

        String v4 = NonNullModel.butReturnNull();
        assertThat(v4).isEqualTo(null);
    }
}
