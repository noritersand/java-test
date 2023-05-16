package builtin.env;

/**
 * <p>System.getProperty()와 System.getenv()의 차이를 테스트
 */
public enum WhatDifferenceBetweenPropertyAndEnv {
    ;

    /**
     * <p>System.getProperty()는 VM 옵션으로 지정한 System properties를 가져온다. (e.g. -Dfoo=bar)
     * <p>System.getenv()는 환경 변수를 가져오는 메서드다. 인텔리제이는 Environment variables로 지정할 수 있음.
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("System.getProperty: foo: " + System.getProperty("foo"));
        System.out.println("System.getProperty: abc: " + System.getProperty("abc"));
        System.out.println("System.getenv: foo: " + System.getenv("foo"));
        System.out.println("System.getenv: abc: " + System.getenv("abc"));
    }

}
