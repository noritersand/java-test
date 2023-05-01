package jdk.java.util.stream;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * java.util.stream.Collector 사용 방법 테스트
 *
 * @author fixalot
 * @since 2023-05-01
 */
@Slf4j
public class CollectorTest {

    @Getter
    private class Dummy {
        private String group;
        private String name;

        public Dummy(String group, String name) {
            this.group = group;
            this.name = name;
        }
    }

    public List<Dummy> generateDummyList() {
        List<Dummy> list = new LinkedList<>();
        list.add(new Dummy("A", "fixalot"));
        list.add(new Dummy("A", "fixalot2"));
        list.add(new Dummy("B", "fixalot3"));
        list.add(new Dummy("B", "fixalot4"));
        return list;
    }

    /**
     * SQL의 group by와 비슷한 Collectors.groupingBy() 테스트
     */
    @Test
    public void testGroupingBy() {
        List<Dummy> hashMaps = this.generateDummyList();
        Map<String, List<Dummy>> collect = hashMaps.stream()
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
        assertEquals(2, aDummy.size());
        assertEquals("A", aDummy.get(0).getGroup());
        assertEquals("fixalot2", aDummy.get(1).getName());

        List<Dummy> bDummy = collect.get("B");
        assertEquals(2, bDummy.size());
        assertEquals("B", bDummy.get(0).getGroup());
        assertEquals("fixalot4", bDummy.get(1).getName());
    }
}
