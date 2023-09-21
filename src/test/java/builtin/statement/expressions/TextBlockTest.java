package builtin.statement.expressions;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Java 15에 추가된 새 기능
 *
 * @author fixalot
 * @since 2023-08-28
 */
@Slf4j
public class TextBlockTest {

    @Test
    public void basicUsage() {
        String str = """
                Hello every-wan!
                This is Waldo!
                """;
        assertThat(str).isEqualTo("Hello every-wan!\nThis is Waldo!\n");
        log.debug("str: {}", str);

        String str2 = """
                    Hello every-wan!
                This is 
                        Waldo!
                    """;
        assertThat(str2).isEqualTo("    Hello every-wan!\n" +
                "This is\n" +
                "        Waldo!\n");
        log.debug("str2: {}", str2);
    }

    @Test
    public void withFormatter() {
        String json = """
                [
                    {
                        "name": "%s",
                        "age": "%d"
                    },
                    {
                        "name": "%s",
                        "age": "%d"
                    }
                ]
                """;

        String json2 = """
                [
                    {
                        "name": "%s",
                        "age": "%d"
                    },
                    {
                        "name": "%s",
                        "age": "%d"
                    }
                ]
                """.formatted("Waldo", 42, "Wendy", 102);
        assertThat(json).isEqualTo("[\n" +
                "    {\n" +
                "        \"name\": \"Waldo\",\n" +
                "        \"age\": \"42\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"name\": \"Wendy\",\n" +
                "        \"age\": \"102\"\n" +
                "    }\n" +
                "]\n");
        log.debug("json: {}", json);
    }
}
