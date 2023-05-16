package builtin.statement;

import com.google.common.base.CaseFormat;
import constant.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * enum 테스트
 *
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class EnumTest {

    public static void main(String[] args) {
        System.out.println(Color.RED); // RED
        System.out.println(Color.RED); // RED
        System.out.println(Color.RED.name()); // RED

        // Display price of Winsap.
        System.out.println(Fruit.APPLE.price());
        // Display all apples and prices.
        System.out.println("All apple prices:");
        for (Fruit ele : Fruit.values()) {
            System.out.println(ele + " costs " + ele.price() + " cents.");
        }

        SystemType var1 = SystemType.BACK_OFFICE;
        SystemType var2 = SystemType.FRONT_OFFICE;

        System.out.println(var1); // BACK_OFFICE
        System.out.println(var2); // FRONT_OFFICE
        System.out.println(var2.name()); // FRONT_OFFICE
        System.out.println(var2.getLabel()); // front office system
    }

    /**
     * toString()을 오버라이딩하고 호출하면 뭐가 나오나
     */
    @Test
    void overrideToStringTest() {
        // 응 안됨
        assertNotEquals("10", InvokeMe.TEN);
        assertEquals(InvokeMe.TEN, InvokeMe.TEN);
    }

    @Test
    void useEqualityOperator() {
        Color red = Color.RED;
        assertSame(Color.RED, red);
    }

    @Test
    void useSwitch() {
        String div = "back_office";
        switch (SystemType.valueOf(div.toUpperCase())) {
            case BACK_OFFICE:
                log.debug("백");
                break;
            case FRONT_OFFICE:
                log.debug("프론트");
                break;
        }
    }

    @Test
    void fromString() {
        Class<Color> clazz = Color.class;
        String source = "red";
        Enum<Color> color = Enum.valueOf(clazz, source.trim().toUpperCase());
        assertEquals(Color.RED, color);
    }

    @Test
    void testCustomValueOf() {
        Fruit target = Fruit.valueOf(12);
        assertEquals(Fruit.COCONUT, target);

    }

    @Test
    void testValueOfIAESafe() {
        assertNull(SystemType.valueOfIAESafe("NOT_BACK_OFFICE"));
    }

    /**
     * Enum.getClass()와 Enum.getDeclaringClass()의 차이
     *
     * @author fixalot
     */
    @Test
    void testGetDeclaringClass() {
        // 평범한 enum은 getClass()나 getDeclaringClass()나 별 차이 없지만
        assertEquals(SystemType.BACK_OFFICE.getClass(), SystemType.BACK_OFFICE.getDeclaringClass());
        assertEquals(SystemType.FRONT_OFFICE.getDeclaringClass(), SystemType.BACK_OFFICE.getDeclaringClass());

        // 클래스 본문이 있는 enum은 getClass()가 내부의 서브클래스를 반환함.
        assertNotEquals(ImSpecial.class, ImSpecial.A.getClass());
        assertNotEquals(ImSpecial.class, ImSpecial.B.getClass());
        assertEquals(ImSpecial.class, ImSpecial.A.getDeclaringClass());
        assertEquals(ImSpecial.class, ImSpecial.B.getDeclaringClass());
    }

    /**
     * enum 확장 하기. 구글 구아바의 {@link com.google.common.base.CaseFormat}을 참고할 것.
     *
     * @author fixalot
     */
    @Test
    void howExtendEnum() {
        ImSpecial.A.destroySelf();
        ImSpecial.B.destroySelf();

        String result = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, "myName");
        assertEquals("MY_NAME", result);
    }

    /**
     * Enum 상수의 선언된 순서(Zero-based indexing)를 반환하는 ordinal() 메서드 테스트
     */
    @Test
    void testOrdinal() {
        assertEquals(0, Fruit.APPLE.ordinal());
        assertEquals(1, Fruit.BANANA.ordinal());
        assertEquals(2, Fruit.COCONUT.ordinal());
    }

    @Test
    void heritanceTest() {
        SubEnum dummy = SubEnum.DUMMY;
        assertEquals(1, dummy.ONE);
        assertEquals(1, SuperEnum.ONE);
        assertEquals(1, SuperEnum.ONE);
        assertEquals("1234", SuperEnum.doSomething());
        //		dummy.doSomething();
//		SubEnum.doSomething();

        assertEquals("5678", SubEnum.DUMMY.overrideMe());

        // TODO SuperEnum, SubEnum 만지다 말았음.
    }

    @Test
    void testCrudModeClass() {
        assertEquals(CrudMode.READ, CrudMode.of("R"));
        assertNull(CrudMode.of("READ"));
        assertNull(CrudMode.of("X"));

        assertEquals(CrudMode.READ, CrudMode.valueOfIAESafe("READ"));
        assertNull(CrudMode.valueOfIAESafe("R"));
        assertNull(CrudMode.valueOfIAESafe("X"));
    }

    private enum CrudMode {
        READ("R"), CREATE("C"), UPDATE("U"), DELETE("D");

        private final String label;

        CrudMode(String label) {
            this.label = label;
        }

        public String label() {
            return label;
        }

        public static CrudMode of(String label) {
            CrudMode[] values = CrudMode.values();
            for (CrudMode ele : values) {
                if (ele.label().equals(label)) {
                    return ele;
                }
            }
            return null;
        }

        public static CrudMode valueOfIAESafe(String name) {
            try {
                return CrudMode.valueOf(name);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
    }

}
