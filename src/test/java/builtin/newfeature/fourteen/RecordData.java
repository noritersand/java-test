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
    // 스태틱 필드는 일반 클래스와 같음
    public static Integer num2;

    // 인스턴스 필드는 선언할 수 없음
//        public final String str; // COMPILE ERROR: Instance field is not allowed in record

    // 얘도 안된다.
//        public RecordData() {} // COMPILE ERROR: Non-canonical record constructor must delegate to another constructor

    // Compact Constructor
    public RecordData {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("text must not be null or blank");
        }
        if (decimal == null || decimal < 0) {
            throw new IllegalArgumentException("decimal must not be null or negative");
        }
    }
}
