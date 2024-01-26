package builtin.statement;

class NestedClassFactory {
    /**
     * 인스턴스 생성 방지용 생성자 선언. 중첩 클래스와는 관계 없음.
     */
    private NestedClassFactory() {
    }

    /**
     * 중첩 클래스 #1
     */
    class NestedClass {
    }

    /**
     * 중첩 클래스 #2
     */
    private class NestedHiddenClass {
        public String getValue() {
            return "fire egg";
        }
    }

    public static NestedClass getNestedClass() {
        NestedClass inner = new NestedClassFactory().new NestedClass();
        return inner;
    }

    public static NestedHiddenClass getNestedHiddenClass() {
        NestedHiddenClass inner = new NestedClassFactory().new NestedHiddenClass();
        return inner;
    }

    public static String getValueOfNestedHiddenClass() {
        NestedHiddenClass inner = new NestedClassFactory().new NestedHiddenClass();
        return inner.getValue();
    }
}
