package jdk.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class ClassClassTest {
    @Test
    void shouldNotEquals() {
        assertNotEquals(String.class, String[].class);
    }

    @Test
    void testGetClass() {
        MyClass my = new MyClass();
        assertEquals(MyClass.class, my.getClass());
        assertNotEquals(MyClass.class, my.getClass().getClass());
        assertEquals(Class.class, my.getClass().getClass());
        assertEquals(Class.class, my.getClass().getClass().getClass().getClass().getClass().getClass().getClass().getClass()); // 고만해
    }

    @Test
    void testGetPackage() {
        assertEquals("jdk.java.lang", ClassClassTest.class.getPackage().getName());
    }

    @Test
    void testForName() throws Exception {
        Class<?> cls = Class.forName("jdk.lang.MyClass");
        assertEquals(java.lang.Class.class, cls.getClass());
        assertEquals("jdk.java.lang.MyClass", cls.getName());
        @SuppressWarnings("deprecation")
        MyClass my2 = (MyClass) cls.newInstance();
        assertEquals(MyClass.class, my2.getClass());
    }

    @Test
    @SuppressWarnings("serial")
    void testGetName() {
        // standard
        assertEquals("java.util.AbstractMap", AbstractMap.class.getName());
        // inner class
        assertEquals("java.util.AbstractMap$SimpleEntry", AbstractMap.SimpleEntry.class.getName());
        // anonymous inner class, $1은 첫 번째로 생성된 익명 내부 클래스를 의미한다.
        assertEquals("jdk.java.lang.ClassClassTest$1", new Serializable() {
        }.getClass().getName());
    }

    @Test
    @SuppressWarnings("serial")
    void testGetTypeName() {
        // standard
        assertEquals("java.util.AbstractMap", AbstractMap.class.getTypeName());
        // inner class
        assertEquals("java.util.AbstractMap$SimpleEntry", AbstractMap.SimpleEntry.class.getTypeName());
        // anonymous inner class, $2은 첫 번째로 생성된 익명 내부 클래스를 의미한다.
        assertEquals("jdk.java.lang.ClassClassTest$2", new Serializable() {
        }.getClass().getTypeName());
    }

    @Test
    @SuppressWarnings("serial")
    void testGetCanonicalName() {
        // standard
        assertEquals("java.util.AbstractMap", AbstractMap.class.getCanonicalName());
        // inner class
        assertEquals("java.util.AbstractMap.SimpleEntry", AbstractMap.SimpleEntry.class.getCanonicalName());
        // anonymous inner class
        assertNull(new Serializable() {
        }.getClass().getCanonicalName());
    }

    @Test
    @SuppressWarnings("serial")
    void testGetSimpleName() {
        assertEquals("Object", Object.class.getSimpleName());
        assertEquals("String", String.class.getSimpleName());
        assertEquals("Integer", Integer.class.getSimpleName());
        assertEquals("Boolean", Boolean.class.getSimpleName());

        // standard
        assertEquals("AbstractMap", AbstractMap.class.getSimpleName());
        // inner class
        assertEquals("SimpleEntry", AbstractMap.SimpleEntry.class.getSimpleName());
        // anonymous inner class
        assertEquals("", new Serializable() {}.getClass().getSimpleName());
    }

    @Test
    void testCurrentMethodName() {
        // #1: 익명 클래스를 생성하고, 해당 클래스가 생성된 환경의 정보 중 메서드 이름을 가져오는 일종의 꼼수. 컴파일 시 불필요한 클래스가 생성되는것에 주의할 것
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        assertEquals("testCurrentMethodName", methodName);

        /*
         * #2: 현재 쓰레드의 스택을 거꾸로 올라가서 메서드명을 가져오는 방법.
         * 소스 출처: https://stackoverflow.com/questions/442747/getting-the-name-of-the-currently-executing-method
         * 버추얼 머신에 따라 다른 결과가 나올 수 있다고 함.
         * 원문: Some virtual machines may, under some circumstances, omit one or more stack frames from the stack trace.
         * 	In the extreme case, a virtual machine that has no stack trace information concerning
         * 	this thread is permitted to return a zero-length array from this method.
         */
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        log.debug(Arrays.toString(ste));
        assertEquals("getStackTrace", ste[0].getMethodName());
        assertEquals("testCurrentMethodName", ste[1].getMethodName());
    }

    @Test
    void testCurrentClassName() {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        log.debug(Arrays.toString(ste));
        assertEquals("jdk.java.lang.ClassClassTest", ste[1].getClassName());
    }
}

class My<T> {

}

class MyClass {

}
