package io.everyone.travel.core.util;

public class HtmlSupports {

    private HtmlSupports() {

    }

    // HTML XSS 방지 치환용
    private static final String[][] BASIC_ESCAPE = {
        {"\"", "&quot;"}, // "
        {"&", "&amp;"},   // &
        {"<", "&lt;"},    // <
        {">", "&gt;"},    // >
    };

    // 기본 치환값을 역순으로 한다. [&, &amp] -> [&amp, &]
    private static final String[][] UNESCAPE = invert(BASIC_ESCAPE);


    public static String escape(String input) {
        return translate(BASIC_ESCAPE, input);

    }

    public static String unescape(String input) {
        return translate(UNESCAPE, input);
    }

    private static String translate(String[][] escape, String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        for (int i = 0; i < escape.length; i++) {
            input = input.replaceAll(escape[i][0], escape[i][1]);
        }
        return input;
    }

    private static String[][] invert(String[][] origin) {
        String[][] invert = new String[origin.length][2];
        for (int i = 0; i < origin.length; i++) {
            invert[i][0] = origin[i][1];
            invert[i][1] = origin[i][0];
        }
        return invert;
    }


}
