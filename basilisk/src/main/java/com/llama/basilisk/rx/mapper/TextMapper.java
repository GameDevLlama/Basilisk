package com.llama.basilisk.rx.mapper;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Christian Ringshofer on 26.08.15.
 */
public abstract class TextMapper extends Mapper {

    @Override
    public Object call(Object string) {
        return this.map((String) string);
    }

    public abstract String map(final String string);

    public static TextMapper reverse() {

        return new TextMapper() {
            @Override
            public String map(String string) {
                String reversed = "";
                for (int i = string.length() - 1; i >= 0; i--) {
                    reversed += string.charAt(i);
                }
                return reversed;
            }
        };

    }

    public static TextMapper regexReplace(final String regex, final String replace) {

        return new TextMapper() {

            private final Pattern pattern = Pattern.compile(regex);
            private final Matcher matcher = pattern.matcher("");

            @Override
            public String map(String string) {
                this.matcher.reset(string);
                if (this.matcher.matches()) return this.matcher.replaceAll(replace);
                return string;
            }
        };

    }

    public static TextMapper caps() {

        return new TextMapper() {
            @Override
            public String map(String string) {
                return string.toUpperCase();
            }
        };

    }

    public static TextMapper explode() {

        return new TextMapper() {
            @Override
            public String map(String string) {
                String explodedString = "";
                for (int i = 0; i < string.length(); i++) {
                    final char c = string.charAt(i);
                    explodedString += c;
                    if (i < string.length() - 1) explodedString += c == ' ' ? " " : "\u00A0";
                }
                return explodedString;
            }
        };

    }

    public static TextMapper sort() {

        return new TextMapper() {
            @Override
            public String map(String string) {
                char[] chars = string.toCharArray();
                Arrays.sort(chars);
                return String.valueOf(chars);
            }
        };

    }

}
