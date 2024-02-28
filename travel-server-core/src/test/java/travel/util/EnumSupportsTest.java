package travel.util;

import io.everyone.travel.core.domain.travel.enums.Nation;
import io.everyone.travel.core.support.EnumSupports;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EnumSupportsTest {

    @Test
    void test() {
        Nation KR = EnumSupports.byEnumName(Nation.class, "KR");

        assertThat(KR).isNotNull();
        assertThat(KR.getEng()).isEqualTo("KOREA, REPUBLIC OF");
        assertThat(KR.getKr()).isEqualTo("대한민국");
    }

}