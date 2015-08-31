package com.llama.basilisk.rx.mapper;

import com.llama.basilisk.binder.Property;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Christian Ringshofer on 26.08.15.
 */
public class TextMapper extends Mapper
{

    private TextMapper(final Property property)
    {
        super(property);
    }

    private TextMapper()
    {
        this(Property.TEXT);
    }

    public static TextMapper create()
    {
        final TextMapper textMapper = new TextMapper();
        textMapper.addMapper(textMapper);
        return textMapper;
    }

    @Override
    public Object call(Object string)
    {
        return this.map((String) string);
    }

    public String map(final String string)
    {
        return string;
    }

    public TextMapper reverse()
    {

        this.addMapper(new TextMapper()
        {
            @Override
            public String map(String string)
            {
                String reversed = "";
                for (int i = string.length() - 1; i >= 0; i--)
                {
                    reversed += string.charAt(i);
                }
                return reversed;
            }
        });
        return this;

    }

    public TextMapper regexReplace(final String regex, final String replace)
    {

        this.addMapper(new TextMapper()
        {

            private final Pattern pattern = Pattern.compile(regex);
            private final Matcher matcher = pattern.matcher("");

            @Override
            public String map(String string)
            {
                this.matcher.reset(string);
                if (this.matcher.matches()) return this.matcher.replaceAll(replace);
                return string;
            }
        });
        return this;

    }

    public TextMapper caps()
    {

        this.addMapper(new TextMapper()
        {
            @Override
            public String map(String string)
            {
                return string.toUpperCase();
            }
        });
        return this;

    }

    public TextMapper explode()
    {

        this.addMapper(new TextMapper()
        {
            @Override
            public String map(String string)
            {
                String explodedString = "";
                for (int i = 0; i < string.length(); i++)
                {
                    final char c = string.charAt(i);
                    explodedString += c;
                    if (i < string.length() - 1) explodedString += c == ' ' ? " " : "\u00A0";
                }
                return explodedString;
            }
        });
        return this;

    }

    public TextMapper sort()
    {

        this.addMapper(new TextMapper()
        {
            @Override
            public String map(String string)
            {
                char[] chars = string.toCharArray();
                Arrays.sort(chars);
                return String.valueOf(chars);
            }
        });
        return this;

    }

}
