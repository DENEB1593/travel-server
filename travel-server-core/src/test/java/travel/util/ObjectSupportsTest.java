package travel.util;

import io.everyone.travel.core.util.ObjectUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ObjectSupportsTest {

    @Test
    void objectIfNotNullTest() {
        Integer val = 10;
        Integer output = ObjectUtils.ifNotNull(val, n -> n * 10);
        assertThat(output).isEqualTo(100);
    }

    @Test
    void stringIfNotNullTest() {
        String val = "hello";
        String output = ObjectUtils.ifNotNull(val, String::toUpperCase);
        assertThat(output).isEqualTo("HELLO");
    }

    @Test
    void collectionIfNotNullTest() {
        List<String> val = List.of("1", "2", "3");
        List<Integer> output = ObjectUtils.ifNotNull(val, (list) -> list.stream().map(Integer::parseInt).toList());
        assertThat(output).allMatch(e -> e > 0);
    }


}
