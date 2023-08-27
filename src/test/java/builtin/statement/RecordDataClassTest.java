package builtin.statement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * <p>Java 14에 추가된 새 유형의 클래스</p>
 * <p>toString(), equals(), hashCode() 메서드와 모든 필드를 초기화하는 생성자가 자동 생성됨</p>
 * <p>Immutable 객체((=초기화 후 읽기만 가능한 객체)라서 모든 필드는 초기화 후 변경 불가</p>
 * <p>아래처럼 클래스 이름 앞에 record 키워드가 오고 클래스 이름 뒤에는 괄호가 옮</p>
 * <p>괄호에 필드를 작성하는 식이다.</p>
 * <pre>
 * public record ClassName() {}
 * </pre>
 * 
 * @author fixalot
 * @since 2023-08-28
 */
@Slf4j
public class RecordDataClassTest {

    @Test
    public void basicUsage() {
        // 필드 초기화 생성자를 명시하지 않아도(lombok이 없어도) 자동 생성됨.
        RecordData rd = new RecordData("abc", 123.45);

        // 자동 생성되는 모든 필드는 private final로 선언된다.
        // 따라서 필드에 직접 접근하거나 재할당은 불가능함
//        log.debug("{}", rd.text); // COMPILE ERROR: 'text' has private access in 'builtin.statement.RecordData'
//        rd.text = "qwe"; // COMPILE ERROR

        // 대신 자동 생성되는 getter를 통해 읽기는 가능함
        // record class의 getter는 'get'이 빠진 형태
        assertThat(rd.text()).isEqualTo("abc");
        assertThat(rd.decimal()).isEqualTo(123.45);
    }

}

