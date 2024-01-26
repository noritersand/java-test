package builtin.stream;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

/**
 * java.util.stream.Collector 사용 방법 테스트
 *
 * @author fixalot
 * @since 2023-05-01
 */
@Slf4j
class CollectorTest {

    /**
     * Collectors.joining() 테스트
     */
    @Test
    void testJoining() {
        List<String> list = new LinkedList<>();
        list.add("eagle1");
        list.add("eagle2");
        list.add("eagle3");
        list.add("eagle4");
        list.add("eagle5");

        String joined = list.stream()
                .collect(Collectors.joining(", "));

        assertThat(joined).isEqualTo("eagle1, eagle2, eagle3, eagle4, eagle5");
    }

    /**
     * SQL의 group by와 비슷한 Collectors.groupingBy() 테스트
     */
    @Test
    void testGroupingBy() {
        List<Dummy> dummies = new LinkedList<>();
        dummies.add(new Dummy("A", "fixalot"));
        dummies.add(new Dummy("A", "fixalot2"));
        dummies.add(new Dummy("B", "fixalot3"));
        dummies.add(new Dummy("B", "fixalot4"));

        Map<String, List<Dummy>> collect = dummies.stream()
                .collect(Collectors.groupingBy(Dummy::getGroup));

        /*
        JSON으로 그려보면 이 모양이:
        [
          {
            group: 'A', name: 'fixalot',
            group: 'A', name: 'fixalot2',
            group: 'B', name: 'fixalot3',
            group: 'B', name: 'fixalot4'
          }
        ]

        이런 모양으로 바뀌는 것:
        {
          "A": [
            {
              group: 'A', name: 'fixalot'
            },
            {
              group: 'A', name: 'fixalot2'
            }
          ],
          "B": [
            {
              group: 'B', name: 'fixalot3'
            },
            {
              group: 'B', name: 'fixalot4'
            }
          ]
        }
        */

        List<Dummy> aDummy = collect.get("A");
        assertThat(aDummy.size()).isEqualTo(2);
        assertThat(aDummy.get(0).getGroup()).isEqualTo("A");
        assertThat(aDummy.get(1).getName()).isEqualTo("fixalot2");

        List<Dummy> bDummy = collect.get("B");
        assertThat(bDummy.size()).isEqualTo(2);
        assertThat(bDummy.get(0).getGroup()).isEqualTo("B");
        assertThat(bDummy.get(1).getName()).isEqualTo("fixalot4");
    }

    @Getter
    private class Dummy {
        private String group;
        private String name;

        public Dummy(String group, String name) {
            this.group = group;
            this.name = name;
        }
    }
}




















