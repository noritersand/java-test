package thirdparty.com.fasterxml.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * com.fasterxml.jackson 테스트
 *
 * @author fixalot
 * @since 2019-01-23
 */
@Slf4j
class JacksonTest {

    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * 파싱할 문자열이 array[]인지 object{}인지 알 수 없을때 처리 방법
     *
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     * @author noritersand
     */
    @Test
    void testSingleValueAsArray() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // 원래는 이런 문자만 파싱할 수 있지만...
//		String text = "{\"aaa\": \"Hello world!\", \"bbb\": [{\"ccc\": \"welcome to dead man club\"}]}";

        /*
         * SINGLE_VALUE_AS_ARRAY 옵션을 설정하면 배열이 아니어도 파싱 가능
         * 만약 이 설정이 없으면: MismatchedInputException: Cannot deserialize instance of
         * `java.util.ArrayList<thirdparty.com.fasterxml.jackson.ElementObject>` out of START_OBJECT token
         */
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        String text = "{\"aaa\": \"Hello world!\", \"bbb\": {\"ccc\": \"welcome to dead man club\"}}";

        UpperObject upper = mapper.readValue(text, UpperObject.class);
        assertNotNull(upper);
        List<ElementObject> elementList = upper.getBbb();
        assertEquals(1, elementList.size());
        ElementObject element = elementList.get(0);
        assertEquals(ElementObject.class, element.getClass());
        assertEquals("welcome to dead man club", element.getCcc());
    }

    /**
     * JSON 문자열 -> 인스턴스
     *
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     * @author fixalot
     */
    @Test
    void testReadValue() throws Exception {
        String jsonText = "[{\"html1\":\"<p>홀홀</p>\",\"html2\":\"<p>ㅗㅎ롷ㄹ</p>\"}]";
        List<HashMap<String, Object>> collection
                = this.mapper.readValue(jsonText, new TypeReference<ArrayList<HashMap<String, Object>>>() {
        });
        assertEquals(1, collection.size());
        assertEquals("<p>홀홀</p>", collection.get(0).get("html1"));
        assertEquals("<p>ㅗㅎ롷ㄹ</p>", collection.get(0).get("html2"));
        assertEquals(ArrayList.class, collection.getClass());
        assertEquals(HashMap.class, collection.get(0).getClass());
    }

    @Test
    void testReadValueWithAmbiguousTypeDeclare() throws Exception {
        String jsonText = "[{\"html1\":\"<p>홀홀</p>\",\"html2\":\"<p>ㅗㅎ롷ㄹ</p>\"}]";
        List<Object> collection
                = this.mapper.readValue(jsonText, new TypeReference<List<Object>>() {
        });
        assertEquals(1, collection.size());
        assertEquals(ArrayList.class, collection.getClass());
        assertEquals(LinkedHashMap.class, collection.get(0).getClass());
    }

    /**
     * 인스턴스 -> JSON 문자열
     *
     * @throws JsonProcessingException
     * @author fixalot
     */
    @Test
    void testWriteValue() throws JsonProcessingException {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "steave");
        map.put("age", "32");
        map.put("job", "baker");
        String jsonText = this.mapper.writeValueAsString(map);
        assertEquals("{\"name\":\"steave\",\"job\":\"baker\",\"age\":\"32\"}", jsonText);
    }

    @Test
    void testWriteValueWithCustomPOJO() throws JsonProcessingException {
        class Pojo {
            private String id;
            private String name;

            public String getId() {
                return this.id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return this.name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        Pojo e1 = new Pojo();
        e1.setId("123");
        e1.setName("abc");
        assertEquals("{\"id\":\"123\",\"name\":\"abc\"}", this.mapper.writeValueAsString(e1));

        List<Pojo> list = new ArrayList<>(1);
        list.add(e1);
        assertEquals("[{\"id\":\"123\",\"name\":\"abc\"}]", this.mapper.writeValueAsString(list));
    }

    /**
     * 컬렉션 -> Plain Object로 변환
     *
     * @author fixalot
     */
    @Test
    void testConvertValue() {
        // #1: map -> po
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "steave");
        map.put("age", "32");
        map.put("dead", "true");

        PlainObject po = this.mapper.convertValue(map, PlainObject.class);
        assertEquals("steave", po.getName());
        assertEquals(Integer.valueOf(32), po.getAge());

        // #2: list<map> -> po
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("name", "soap");
        map2.put("age", 43);
        map2.put("dead", true);

        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        list.add(map);
        list.add(map2);

        ArrayList<PlainObject> poList = this.mapper.convertValue(list, new TypeReference<ArrayList<PlainObject>>() {
        });
        assertEquals("soap", poList.get(1).getName());
        assertEquals(Integer.valueOf(32), poList.get(0).getAge());
        assertEquals(Integer.valueOf(43), poList.get(1).getAge());
        assertTrue(poList.get(0).isDead());
        assertTrue(poList.get(1).isDead());
    }

    @Test
    void testJsonFormat() throws JsonProcessingException {
        String str = "{\"birthDate\":\"2018-01-01\"}";
        mapper.registerModule(new JavaTimeModule());
        ParseMe parseMe = mapper.readValue(str, ParseMe.class);
        assertEquals(LocalDate.of(2018, 1, 1), parseMe.getBirthDate());
    }

    @Getter
    @Setter
    private static class ParseMe {
        private LocalDate birthDate;
    }
}
