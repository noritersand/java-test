package lab.util.json;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * 커스텀 TypeAdeptor가 선언된 GsonBuilder 테스트
 *
 * @author fixalot
 * @since 2017-12-01
 */
@Slf4j
public class GsonBuilderTest {

    @Test
    void testFromJsonForPlainObject() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.getGson();
        GsonTestModel convertedModel = gson.fromJson(makeJsonObjectText(), GsonTestModel.class);
        assertNull(convertedModel.getCharValue());
        assertEquals("", convertedModel.getStringValue());
        assertNull(convertedModel.getShortValue());
        assertNull(convertedModel.getIntValue());
        assertNull(convertedModel.getLongValue());
        assertNull(convertedModel.getDoubleValue());
        assertNull(convertedModel.getBigDecimalValue());
        assertNull(convertedModel.getBoolValue());
    }

    @Test
    void testFromJsonForArray() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.getGson();
        GsonTestModel[] convertedModel = gson.fromJson(makeJsonArrayText(), GsonTestModel[].class);
        log.debug(Arrays.toString(convertedModel));
    }

    @Test
    void testFromJsonForArrayInArray() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.getGson();
        GsonTestModel[] convertedModel = gson.fromJson(makeJsonArrayInArrayText(), GsonTestModel[].class);
        log.debug(Arrays.toString(convertedModel));
    }

    @Test
    void testFromJsonForArrayInArrayToList() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.getGson();

        @SuppressWarnings("serial")
        List<GsonTestModel> convertedModelList = gson.fromJson(makeJsonArrayInArrayText(), new TypeToken<ArrayList<GsonTestModel>>() {
        }.getType());

        GsonTestChild[] convertedModel = convertedModelList.get(0).getGsonTestChild();
        assertEquals(1, convertedModel.length);
        assertEquals(GsonTestChild[].class, convertedModel.getClass());

        GsonTestChild gsonTestChild = convertedModel[0];
        assertEquals("1", gsonTestChild.getA());
        assertEquals("2", gsonTestChild.getB());
    }

    public String makeJsonObjectText() {
        String text = "{" +
                "\"stringValue\":\"\"" +
                "," + "\"imAlone\":\"\"" +
                "," + "\"charValue\":\"\"" +
                "," + "\"shortValue\":\"\"" +
                "," + "\"intValue\":\"\"" +
                "," + "\"longValue\":\"\"" +
                "," + "\"doubleValue\":\"\"" +
                "," + "\"bigDecimalValue\":\"\"" +
                "," + "\"boolValue\":\"\"" +
//				.append(",").append("\"dateValue\":\"1999-01-01\"")
                "}";
        log.debug("text: {}", text);
        return text;
    }

    public String makeJsonArrayText() {
        String text = "[" +
                "{" +
                "\"stringValue\":\"\"" +
                "," + "\"charValue\":\"\"" +
                "," + "\"shortValue\":\"\"" +
                "," + "\"intValue\":\"\"" +
                "," + "\"longValue\":\"\"" +
                "," + "\"doubleValue\":\"\"" +
                "," + "\"bigDecimalValue\":\"\"" +
                "," + "\"boolValue\":\"\"" +
//				.append(",").append("\"dateValue\":\"\"")
                "}" +
                "]";
        log.debug("text: {}", text);
        return text;
    }

    public String makeJsonArrayInArrayText() {
        String text = "[" +
                "{" +
                "\"stringValue\":\"\"" +
                "," + "\"charValue\":\"\"" +
                "," + "\"shortValue\":\"\"" +
                "," + "\"intValue\":\"\"" +
                "," + "\"longValue\":\"\"" +
                "," + "\"doubleValue\":\"\"" +
                "," + "\"bigDecimalValue\":\"\"" +
                "," + "\"boolValue\":\"\"" +
                "," + "\"gsonTestChild\": [{ \"a\":\"1\", \"b\":\"2\" }]" +
                "}" +
                "]";
        log.debug("text: {}", text);
        return text;
    }

    @SuppressWarnings("unused")
    private class GsonTestModel {
        private Character charValue;
        private String stringValue;
        private Short shortValue;
        private Integer intValue;
        private Long longValue;
        private Double doubleValue;
        private BigDecimal bigDecimalValue;
        private Boolean boolValue;
        private Date dateValue;

        private GsonTestChild[] gsonTestChild;

        public GsonTestChild[] getGsonTestChild() {
            return gsonTestChild;
        }

        public void setGsonTestChild(GsonTestChild[] gsonTestChild) {
            this.gsonTestChild = gsonTestChild;
        }

        public Date getDateValue() {
            return dateValue;
        }

        public void setDateValue(Date dateValue) {
            this.dateValue = dateValue;
        }

        public Character getCharValue() {
            return charValue;
        }

        public void setCharValue(Character charValue) {
            this.charValue = charValue;
        }

        public String getStringValue() {
            return stringValue;
        }

        public void setStringValue(String stringValue) {
            this.stringValue = stringValue;
        }

        public Short getShortValue() {
            return shortValue;
        }

        public void setShortValue(Short shortValue) {
            this.shortValue = shortValue;
        }

        public Integer getIntValue() {
            return intValue;
        }

        public void setIntValue(Integer intValue) {
            this.intValue = intValue;
        }

        public Long getLongValue() {
            return longValue;
        }

        public void setLongValue(Long longValue) {
            this.longValue = longValue;
        }

        public Double getDoubleValue() {
            return doubleValue;
        }

        public void setDoubleValue(Double doubleValue) {
            this.doubleValue = doubleValue;
        }

        public BigDecimal getBigDecimalValue() {
            return bigDecimalValue;
        }

        public void setBigDecimalValue(BigDecimal bigDecimalValue) {
            this.bigDecimalValue = bigDecimalValue;
        }

        public Boolean getBoolValue() {
            return boolValue;
        }

        public void setBoolValue(Boolean boolValue) {
            this.boolValue = boolValue;
        }

        @Override
        public String toString() {
            Class<?> clazz = this.getClass();
            Field[] fields = clazz.getDeclaredFields();
            StringBuilder returnString = new StringBuilder();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
//					if (null == field.get(this)) {
//						continue; // 값이 없을 때 출력하지 않음
//					}
                    returnString.append(field.getName());
                    returnString.append(" = ");
                    returnString.append(field.get(this));
                } catch (IllegalArgumentException e) {
                    returnString.append("IllegalArgumentException occured!!");
                    returnString.append(e);
                } catch (IllegalAccessException e) {
                    returnString.append("IllegalAccessException occured!!");
                    returnString.append(e);
                }
                returnString.append(";\n");
            }
            return returnString.toString();
        }
    }

    @SuppressWarnings("unused")
    private class GsonTestChild {
        private String a;
        private String b;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }

}

