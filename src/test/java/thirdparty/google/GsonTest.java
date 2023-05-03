package thirdparty.google;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonReader;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class GsonTest {

    @Test
    void getDataFromFile() throws IOException {
        JsonReader reader = new JsonReader(new FileReader("src\\test\\resources\\gson-test\\servers.json", StandardCharsets.UTF_8));
        @SuppressWarnings("serial")
        Type listType = new TypeToken<ArrayList<HashMap<String, String>>>() {
        }.getType();
        Object result = new Gson().fromJson(reader, listType);
        assertEquals(new ArrayList<HashMap<String, String>>().getClass(), result.getClass());
        log.debug("{}", result);
    }

    @Test
    void toJSONString() {
        JsonObject job = new JsonObject();
        job.addProperty("key", "value");

        assertEquals("{\"key\":\"value\"}", new Gson().toJson(job));
    }

    @Test
    void parseToPlainObject() {
        String json = "{\"key\":\"first\",\"value\":\"1234\"}";
        MyClass myClass = new Gson().fromJson(json, MyClass.class);
        assertEquals("first", myClass.getKey());
        assertEquals("1234", myClass.getValue());
    }

    @Test
    void parseToCollections() {
        String json = "[{\"key\":\"first\",\"value\":\"1234\"}, {\"key\":\"second\",\"value\":\"5678\"}]";
        @SuppressWarnings("serial")
        Type listType = new TypeToken<ArrayList<MyClass>>() {
        }.getType();
        ArrayList<MyClass> myClassList = new Gson().fromJson(json, listType);
        assertEquals("first", myClassList.get(0).getKey());
        assertEquals("1234", myClassList.get(0).getValue());
        assertEquals("second", myClassList.get(1).getKey());
        assertEquals("5678", myClassList.get(1).getValue());
    }

    @Test
    void parseToCollectionsWithAmbiguousTypeDeclare() {
        String json = "[{\"key\":\"first\",\"value\":\"1234\"}, {\"key\":\"second\",\"value\":\"5678\"}]";
        @SuppressWarnings("serial")
        Type listType = new TypeToken<List<Object>>() {
        }.getType();
        List<Object> list = new Gson().fromJson(json, listType);
        assertEquals(ArrayList.class, list.getClass());
        assertEquals(LinkedTreeMap.class, list.get(0).getClass());
    }
}

class MyClass {
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
