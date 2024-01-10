package builtin.newfeature.fourteen;

/**
 * @see RecordDataClassTest
 *
 * @param text
 * @param decimal
 * 
 * @author fixalot
 * @since 2023-08-28
 */
record RecordData(String text, Double decimal) {
    // 인스턴스 필드는 선언할 수 없음
//        public final String str; // COMPILE ERROR: Instance field is not allowed in record

    // 스태틱 필드는 초기화와 함께 사용 가능
    public static final Integer num = 123;

    // 얘도 안된다.
//        public RecordData() {} // COMPILE ERROR: Non-canonical record constructor must delegate to another constructor
}
