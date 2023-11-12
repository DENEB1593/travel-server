package io.everyone.travel.util;

import io.everyone.travel.domain.enums.Nation;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EnumUtilTest {

    @Test
    void test() {
        Nation KR = EnumUtil.byEnumName(Nation.class, "KR");

        assertThat(KR).isNotNull();
        assertThat(KR.getEng()).isEqualTo("KOREA, REPUBLIC OF");
        assertThat(KR.getKr()).isEqualTo("대한민국");
    }

}