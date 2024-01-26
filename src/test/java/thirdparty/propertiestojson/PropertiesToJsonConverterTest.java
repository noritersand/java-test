package thirdparty.propertiestojson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import pl.jalokim.propertiestojson.util.PropertiesToJsonConverter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class PropertiesToJsonConverterTest {

    @SuppressWarnings("unchecked")
    @Test
    void test() throws IOException {
        String jsonFromProperties = new PropertiesToJsonConverter()
                .convertPropertiesFromFileToJson("src\\test\\resources\\properties-test\\convert-test.properties");
        log.debug(jsonFromProperties);
        HashMap<String, Object> map = new Gson().fromJson(jsonFromProperties, new TypeToken<HashMap<String, Object>>() {
        }.getType());
        map.get("man.insurance.cost");
        Map<String, Object> depth0 = ((Map<String, Object>) map.get("man"));
        Map<String, Object> depth1 = ((Map<String, Object>) depth0.get("insurance"));
        Double depth2 = ((Double) depth1.get("cost"));
        assertEquals(Double.parseDouble("126.543"), depth2, 0);
    }
}
