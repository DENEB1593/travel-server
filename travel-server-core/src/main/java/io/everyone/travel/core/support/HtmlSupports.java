package io.everyone.travel.core.support;

import lombok.experimental.UtilityClass;

@UtilityClass
public class HtmlSupports {

    // HTML XSS 방지 치환용
    private final String[][] BASIC_ESCAPE = {
        {"\"", "&quot;"}, // "
        {"&", "&amp;"},   // &
        {"<", "&lt;"},    // <
        {">", "&gt;"},    // >
    };

    // 기본 치환값을 역순으로 한다. [&, &amp] -> [&amp, &]
    private final String[][] UNESCAPE = invert(BASIC_ESCAPE);


    public String escape(String input) {
        return translate(BASIC_ESCAPE, input);

    }

    public String unescape(String input) {
        return translate(UNESCAPE, input);
    }

    private String translate(String[][] escape, String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        for (String[] strings : escape) {
            input = input.replaceAll(strings[0], strings[1]);
        }
        return input;
    }

    private String[][] invert(String[][] origin) {
        String[][] invert = new String[origin.length][2];
        for (int i = 0; i < origin.length; i++) {
            invert[i][0] = origin[i][1];
            invert[i][1] = origin[i][0];
        }
        return invert;
    }


}
