package thirdparty.projectlombok;

import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Slf4j
class LombokTest {

    @Test
    void testEqualsAndHashCode() {
        Ophan ophan = new Ophan();
        ophan.o = "1234";

        Ophan ophan2 = new Ophan();
        ophan2.o = "1234";

        assertThat(ophan.equals(ophan2)).isTrue();
        assertThat(ophan.hashCode()).isEqualTo(ophan2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeWithCallSuper() {
        SubA subA = new SubA();
        subA.a = "a";
        subA.b = "b";
        SubA anotherEntity2 = new SubA();
        anotherEntity2.a = "aa";
        anotherEntity2.b = "b";

        assertThat(subA.equals(anotherEntity2)).isTrue(); // pass
        assertThat(subA).isEqualTo(anotherEntity2); // pass
        assertThat(subA.hashCode()).isEqualTo(anotherEntity2.hashCode()); // pass

        SubB subB = new SubB();
        subB.a = "a";
        subB.b = "b";
        SubB entity2 = new SubB();
        entity2.a = "aa";
        entity2.b = "b";

        assertThat(subB.equals(entity2)).isFalse(); // pass
        assertThat(subB).isNotEqualTo(entity2); // pass
        assertThat(subB.hashCode()).isNotEqualTo(entity2.hashCode()); // pass
    }

    /**
     * <p>lombok의 @Builder 어노테이션을 적용하면 생성자 대신 빌더 패턴으로 인스턴스를 초기화 할 수 있음.
     * <p>그런데 @Builder만 적용한 경우 기본 생성자가 없어서 곤란한 상황,
     * <p>예를 들어 SpringMVC의 컨트롤러.메서드 파라미터로 쓸 수 없는 사태가 발생할 수 있기 때문에, 기본 생성자를 수동으로 만들어 주는 것이 좋음.
     * <p>근데 또 이러면 java: constructor BuildMe in class thirdparty.org.projectlombok.BuildMe cannot be applied to given types; 컴파일 에러가 나서 @AllArgsConstructor도 추가해야 함(그냥 쓰지 말까...)
     */
    @Test
    void visibilityTest() {
        // 기본 생성자는 안만들어지고, 필드만큼 파라미터를 받는 생성자는 만들어지지만 private이다.
        // 'BuildMe(java.lang.String)' in 'thirdparty.org.projectlombok.BuildMe' cannot be applied to '()'
//        BuildMe bm1 = new BuildMe();
        BuildMe bm2 = new BuildMe("yo", 123); // 이건 패키지 다르면 접근 안되니 주의할 것

        assertThat(bm2.getA()).isEqualTo("yo"); // 이 getter는 @Getter 어노테이션이 만들어줌
        assertThat(bm2.getB()).isEqualTo(123);

        BuildMe bm3 = BuildMe.builder().a("yo").b(123).build();
        assertThat(bm3.getA()).isEqualTo("yo");
        assertThat(bm3.getB()).isEqualTo(123);

        // setter를 만들어주는 건 아니라서 컴파일 에러
//        bm2.setA("hi");

        new BuildMe();
    }

    @Test
    void testSuperBuilder() {
        Child child = Child.builder()
                .a("aaa")
                .b(123)
                .c(256.789).build();

        assertThat(child.getB()).isEqualTo(123);
    }

    @Test
    void testConstructor() {
    //        new Child("aaa", 123, 256.789);
        // 부모의 필드도 초기화하는 생성자를 만들려거든 롬복으로 안되고 직접 만들어야 함
    }
}

@EqualsAndHashCode
class Ophan {
    public String o;
}

@EqualsAndHashCode
class SuperClass {
    public String a;
}

@EqualsAndHashCode
class SubA extends SuperClass {
    public String b;
}

@EqualsAndHashCode(callSuper = true)
class SubB extends SuperClass {
    public String b;
}


@SuppressWarnings("LombokGetterMayBeUsed")
@Builder
@AllArgsConstructor
@Slf4j
class BuildMe {
    public BuildMe() {
    }

    private String a;
    private int b;

    public String getA() {
        return this.a;
    }

    public void setA(String a) {
        log.debug("{}", "@Builder 사용 시 setter는 호출되지 않는다.");
        this.a = a;
    }

    public int getB() {
        return this.b;
    }

    public void setB(int b) {
        log.debug("{}", "@Builder 사용 시 setter는 호출되지 않는다.");
        this.b = b;
    }
}

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
class Child extends Parent {
    private Double c;
}

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
class Parent {
    private String a;
    private int b;
}
