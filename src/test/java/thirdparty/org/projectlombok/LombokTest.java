package thirdparty.org.projectlombok;

import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class LombokTest {

    /**
     * <p>lombok의 @Builder 어노테이션을 적용하면 생성자 대신 빌더 패턴으로 인스턴스를 초기화 할 수 있음.
     * <p>그런데 @Builder만 적용한 경우 기본 생성자가 없어서 곤란한 상황,
     * <p>예를 들어 SpringMVC의 컨트롤러.메서드 파라미터로 쓸 수 없는 사태가 발생할 수 있기 때문에, 기본 생성자를 수동으로 만들어 주는 것이 좋음.
     * <p>근데 또 이러면 java: constructor BuildMe in class thirdparty.org.projectlombok.BuildMe cannot be applied to given types; 컴파일 에러가 나서 @AllArgsConstructor도 추가해야 함(그냥 쓰지 말까...)
     */
    @Test
    public void visibilityTest() {
        // 기본 생성자는 안만들어지고, 필드만큼 파라미터를 받는 생성자는 만들어지지만 private이다.
        // 'BuildMe(java.lang.String)' in 'thirdparty.org.projectlombok.BuildMe' cannot be applied to '()'
//        BuildMe bm1 = new BuildMe();
        BuildMe bm2 = new BuildMe("yo", 123); // 이건 패키지 다르면 접근 안되니 주의할 것

        assertEquals("yo", bm2.getA()); // 이 getter는 @Getter 어노테이션이 만들어줌
        assertEquals(123, bm2.getB());

        BuildMe bm3 = BuildMe.builder().a("yo").b(123).build();
        assertEquals("yo", bm3.getA());
        assertEquals(123, bm3.getB());

        // setter를 만들어주는 건 아니라서 컴파일 에러
//        bm2.setA("hi");

        new BuildMe();
    }

    @Builder
    @Getter
    @AllArgsConstructor
    private static class BuildMe {
        public BuildMe() {
        }

        private String a;
        private int b;
    }

    @Test
    public void testConstructor() {
//        new Child("aaa", 123, 256.789);
        // 부모의 필드도 초기화하는 생성자를 만들려거든 롬복으로 안되고 직접 만들어야 함
    }

    @Test
    public void testSuperBuilder() {
        Child child = Child.builder()
                .a("aaa")
                .b(123)
                .c(256.789).build();

        assertEquals(child.getB(), 123);
    }

    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Child extends Parent {
        private Double c;
    }

    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Parent {
        private String a;
        private int b;
    }
}
