package thirdparty.org.projectlombok;

import lombok.Builder;
import lombok.Getter;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LombokTest {
    private static final Logger logger = LoggerFactory.getLogger(LombokTest.class);

    @Test
    public void testBuilderAnnotation() {
        // 기본 생성자는 안만들어짐
        // 'BuildMe(java.lang.String)' in 'thirdparty.org.projectlombok.BuildMe' cannot be applied to '()'
//        BuildMe bm1 = new BuildMe();

        BuildMe bm2 = new BuildMe("yo", 123);
        Assert.assertEquals("yo", bm2.getA()); // 이 getter는 @Getter 어노테이션이 만들어줌
        Assert.assertEquals(123, bm2.getB());

        BuildMe bm3 = BuildMe.builder().a("yo").b(123).build();
        Assert.assertEquals("yo", bm3.getA());
        Assert.assertEquals(123, bm3.getB());

        // setter를 만들어주는 건 아니라서 컴파일 에러
//        bm2.setA("hi");
    }
}

@Builder
@Getter
class BuildMe {
    private String a;
    private int b;
}